
/*
 *
 *  * Alunos:
 *  * - Nome: Lucas Silva Santana      Matrícula: 202165092C
 *  * - Nome: Ricardo Ervilha Silva       Matrícula: 202165561C
 *  *
 *  * Disciplina: DCC045 - Teoria de Compiladores
 *
 *
 *
 */

package visitors;

import ast.*;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupFile;
import util.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JasminVisitor extends Visitor {

    private STGroup groupTemplate;
    private ST type, stmt, expr;
    private List<ST> funcs, params;

    private String fileName;

    TyEnv<LocalEnv<VarInfo>> env;
    LocalEnv<VarInfo> local;

    private int label = 0;

    public JasminVisitor(TyEnv<LocalEnv<VarInfo>> env) {
        groupTemplate = new STGroupFile("./template/jasmin.stg");
        this.fileName = "Programa";
        this.env = env;
    }


    /**
     * @param program
     */
    @Override
    public void visit(Program program) {
        ST template = groupTemplate.getInstanceOf("program");

        template.add("name", fileName);
        funcs = new ArrayList<ST>();
        /*
            getDefinitions() pode ser Data, AbstractData ou Fun
         */
        for (Def def : program.getDefinitions()) {
            if (def instanceof Fun f) {
                f.accept(this);
            }
        }
        template.add("funcs", funcs);

        System.out.println(template.render());

        // Renderiza o template
        String output = template.render();

        // Define o caminho do arquivo
        Path path = Paths.get(fileName + ".j");

        try {
            Files.write(path, output.getBytes(StandardCharsets.UTF_8));
            System.out.println("Arquivo gerado: " + path.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param f
     */
    @Override
    public void visit(Fun f) {
        ST stFun = groupTemplate.getInstanceOf("func");

        if(f.getID().equals("main")) {//se for a função main, cria como main_aux
            stFun.add("name", "main_aux");
        } else {//senão, cria com o proprio nome da função
            stFun.add("name", f.getID());
        }

        // Variáveis locais da função com informação de tipo
        // * Os parâmetros
        // * Variáveis locais
        local = env.get(f.getID());

        stFun.add("decls", local.getKeys().size()); // número de váriaveis locais, incluíndo os parâmetros
        stFun.add("stack", 10); // tamanho máximo da pilha. Coloquei 10, mas tem que calcular baseado no tamanho das subexpressões

        params = new ArrayList<ST>();
        List<Param> paramsFun = (f.getParams() != null) ? f.getParams().getParamList() : Collections.emptyList();
        for (Param p : paramsFun) {
            p.accept(this);
        }
        stFun.add("params", params);

        f.getCmd().accept(this);
        stFun.add("stmt", stmt);

        if (f.getReturnTypes().isEmpty()) {
            stFun.add("return", "V");//tipo de retorno
            stmt = groupTemplate.getInstanceOf("vreturn");
            stFun.add("stmt", stmt);//comando de retorno
        } else {
            f.getReturnTypes().get(0).accept(this);// por enquanto, pegando apenas o primeiro retorno
            stFun.add("return", type); // vai colocar no type o tipo de retorno
        }

        funcs.add(stFun);
    }

    /**
     * @param b
     */
    @Override
    public void visit(Block b) {
        List<ST> cmds = new ArrayList<>();
        for (Cmd c : b.getCommands()) {
            c.accept(this);
            cmds.add(stmt);
        }
        stmt = groupTemplate.getInstanceOf("block");
        stmt.add("cmds", cmds);
    }

    /**
     * @param p
     */
    @Override
    public void visit(DataDecl p) {

    }

    /**
     * @param p
     */
    @Override
    public void visit(AbstractDataDecl p) {

    }

    /**
     * @param p
     */
    @Override
    public void visit(Decl p) {

    }


    /**
     * @param p
     */
    @Override
    public void visit(FunAbstractData p) {

    }

    /**
     * @param e
     */
    @Override
    public void visit(Params e) {

    }

    /**
     * @param e
     */
    @Override
    public void visit(ArrayExpr e) {

    }

    /**
     * cmd --> lvalue ‘=’ exp ‘;’
     * lvalue --> ID | lvalue '[' exp ']' | lvalue '.' ID | TYID ID;
     * ou seja, posso fazer atribuição em variavel ou atributo de um registro
     * @param cmdAssign
     */
    @Override
    public void visit(CmdAssign cmdAssign) {
        // x = 2
        // LHS = RHS empilhar o RHS (iconst_2) e depois salvar com store o que está na pilha no index da variavel x (istore_1)

        // RHS
        cmdAssign.getExpression().accept(this);
        ST rhsExpr = expr;

        // LHS
        // cmdAssign.getLvalue().accept(this);// TODO: precisa?
        //ST lhsCode = expr; // ou um campo separado se preferir TODO: precisa?

        SType sType = cmdAssign.getExpression().getSType();

        if (cmdAssign.getLvalue() instanceof ID varID) {
            int index = local.get(varID.getName()).getIndex();

            // o código é o mesmo para os tres: istore <index>
            if (sType instanceof STyInt || sType instanceof STyBool || sType instanceof STyChar) {
                stmt = groupTemplate.getInstanceOf("store_int");
            } else if (sType instanceof STyFloat) {
                stmt = groupTemplate.getInstanceOf("store_float");
            }
            stmt.add("index", index);
            stmt.add("rhs", rhsExpr);
        } else if (cmdAssign.getLvalue() instanceof LValueExp) {
            LValueExp arr = (LValueExp) cmdAssign.getLvalue();
            ST arrayRef = expr;/* gera código para referência do array */;
            ST indexExpr = expr;/* gera código para índice */;
            if (sType instanceof STyInt) {
                stmt = groupTemplate.getInstanceOf("astore_int_array");
            } else if (sType instanceof STyFloat) {
                stmt = groupTemplate.getInstanceOf("astore_float_array");
            }
            stmt.add("arrayRef", arrayRef);
            stmt.add("indexExpr", indexExpr);
            stmt.add("rhs", rhsExpr);
        } else if (cmdAssign.getLvalue() instanceof IdLValue) {
            IdLValue fld = (IdLValue) cmdAssign.getLvalue();
            ST objRef = expr; /* gera código para referência do objeto */;
            if (sType instanceof STyInt) {
                stmt = groupTemplate.getInstanceOf("putfield_int");
            } else if (sType instanceof STyFloat) {
                stmt = groupTemplate.getInstanceOf("putfield_float");
            }
            stmt.add("objRef", objRef);
            stmt.add("fieldName", fld.getId());
            stmt.add("fieldDescriptor", fld.getLvalue());
            stmt.add("rhs", rhsExpr);
        }
    }

    /**
     * @param p
     */
    @Override
    public void visit(CmdFuncCall p) {

    }

    /**
     * @param p
     */
    @Override
    public void visit(CmdIf p) {
        // Avalia a condição e guarda o resultado em expr, que vai ter o ST
        p.getCondition().accept(this);
        ST condExpr = expr;

        // Gera o código do then e else e guarda no stmt
        p.getThenCmd().accept(this);
        ST thenStmt = stmt; // stmt guarda o código gerado para then

        ST elseStmt;
        if (p.getElseCmd() != null) {
            p.getElseCmd().accept(this);
            elseStmt = stmt;
        } else {
            // else vazio gera um bloco vazio, apenas para não passar null para o template
            elseStmt = groupTemplate.getInstanceOf("empty");
        }

        // incrementar labels para não repetir
        int elseLabel = label++;
        int endLabel = label++;

        // monta o template
        ST ifElse = groupTemplate.getInstanceOf("if_else");
        ifElse.add("elseNum", elseLabel);
        ifElse.add("endNum", endLabel);
        ifElse.add("expr", condExpr);
        ifElse.add("thenBlock", thenStmt);
        ifElse.add("elseBlock", elseStmt);

        // guarda no stmt o resultado do comando if montado
        stmt = ifElse;
    }

    /**
     * a → Void, em que a ∈ {Int, Char, Bool, Float}
     * @param cmdPrint
     */
    @Override
    public void visit(CmdPrint cmdPrint) {
        cmdPrint.getExpression().accept(this);// esse accept vai jogar o ST na variavel expr

        SType sType = cmdPrint.getExpression().getSType();

        if (sType instanceof STyInt) {
            stmt = groupTemplate.getInstanceOf("iprint");
        } else if (sType instanceof STyFloat) {
            stmt = groupTemplate.getInstanceOf("fprint");
        } else if (sType instanceof STyChar) {
            stmt = groupTemplate.getInstanceOf("cprint");
        } else if (sType instanceof STyBool) {
            stmt = groupTemplate.getInstanceOf("bprint");
        } else {
            throw new RuntimeException("Tipo não suportado no print: " + sType);
        }

        stmt.add("expr", expr);
    }

    /**
     * @param p
     */
    @Override
    public void visit(CmdRead p) {

    }

    /**
     * cmd --> return exp {‘,’ exp} ‘;’
     * @param cmdReturn
     */
    @Override
    public void visit(CmdReturn cmdReturn) {
        /*
            comando retorno, retorna um arraylist de expressões
            se chegou aqui é pq tem pelo menos uma expressão
         */
        cmdReturn.getExpressions().get(0).accept(this);
//        SType t = e.getExpr().getType();
//        if (t instanceof STyInt) {
//            stmt = groupTemplate.getInstanceOf("ireturn");
//        }
        stmt = groupTemplate.getInstanceOf("ireturn");
        stmt.add("expr", expr);
    }

    /**
     * @param p
     */
    @Override
    public void visit(CmdIterate p) {

    }

    private void binOp(BinOP binExpr, String opName) {
        ST aux;
        SType sType = binExpr.getSType();

        boolean isIntLike = sType instanceof STyInt || sType instanceof STyChar; // int e char tratados igual
        boolean isFloat = sType instanceof STyFloat;

        String type = isIntLike ? "i" : "f";


        if (opName.equals("+")) {
            aux = groupTemplate.getInstanceOf(type + "add");
        } else if (opName.equals("-")) {
            aux = groupTemplate.getInstanceOf(type + "sub");
        } else if (opName.equals("*")) {
            aux = groupTemplate.getInstanceOf(type + "mul");
        } else if (opName.equals("/")) {
            aux = groupTemplate.getInstanceOf(type + "div");
        } else if (opName.equals("%")) {
            if (!(sType instanceof STyInt)) {
                throw new RuntimeException(binExpr.getLine() + ", " + binExpr.getCol() +
                        ": Operador % só é suportado para inteiros.");
            }
            aux = groupTemplate.getInstanceOf("imod");
        } else {
            throw new RuntimeException(binExpr.getLine() + ", " + binExpr.getCol() +
                    ": Operador não reconhecido: " + opName);
        }

        binExpr.getLeft().accept(this);
        aux.add("left_expr", expr);
        binExpr.getRight().accept(this);
        aux.add("right_expr", expr);
        expr = aux;
    }

    @Override
    public void visit(Add e) {
        binOp(e, "+");
    }

    @Override
    public void visit(Sub e) {
        binOp(e, "-");
    }

    @Override
    public void visit(Mul e) {
        binOp(e, "*");
    }

    @Override
    public void visit(Div e) {
        binOp(e, "/");
    }

    @Override
    public void visit(Mod e) {
        binOp(e, "%");
    }

    /**
     * [Bool, Bool] → Bool
     *
     * @param e
     */
    @Override
    public void visit(And e) {
        e.getLeft().accept(this);
        ST leftExpr = expr;  // salva o resultado do lado esquerdo

        e.getRight().accept(this);
        ST rightExpr = expr; // salva o resultado do lado direito

        ST andTemplate = groupTemplate.getInstanceOf("and_expr");
        andTemplate.add("left_expr", leftExpr);
        andTemplate.add("right_expr", rightExpr);

        expr = andTemplate;
    }

    /**
     * [a, a] → Bool a ∈ {Int, Float, Char}
     *
     * @param relExpr
     * @param opName
     */
    private void relationalBinOp(BinOP relExpr, String opName) {
        ST aux;
        SType sType = relExpr.getLeft().getSType();

        boolean isIntLike = sType instanceof STyInt || sType instanceof STyChar;

        // Escolher template
        if (opName.equals("==")) {
            aux = groupTemplate.getInstanceOf(isIntLike ? "iequals_expr" : "fequals_expr");
            aux.add("num", label++);
        } else if (opName.equals("!=")) {
            ST eq = groupTemplate.getInstanceOf(isIntLike ? "iequals_expr" : "fequals_expr");
            eq.add("num", label++);


            relExpr.getLeft().accept(this);
            ST left = expr;


            relExpr.getRight().accept(this);
            ST right = expr;

            eq.add("left_expr", left);
            eq.add("right_expr", right);

            // Uso o NOT para inverter
            ST not = groupTemplate.getInstanceOf("not_expr");
            not.add("expr", eq);

            expr = not;
            return;
        } else if (opName.equals("<")) {
            aux = groupTemplate.getInstanceOf(isIntLike ? "ilt_expr" : "flt_expr");
            aux.add("num", label++);
        } else {
            throw new RuntimeException(relExpr.getLine() + ", " + relExpr.getCol() +
                    ": Operador não reconhecido: " + opName);
        }

        // Gerar código para a esquerda
        relExpr.getLeft().accept(this);
        ST left = expr;

        // Gerar código para a direita
        relExpr.getRight().accept(this);
        ST right = expr;

        // Passar expressões geradas para o template
        aux.add("left_expr", left);
        aux.add("right_expr", right);

        expr = aux;
    }

    /**
     * [a, a] → Bool, em que a ∈ {Int, Float, Char}
     * Os dois operandos devem ter o mesmo tipo
     *
     * @param e
     */
    @Override
    public void visit(Eq e) {
        relationalBinOp(e, "==");
    }

    /**
     * [a, a] → Bool, em que a ∈ {Int, Float, Char}
     * Os dois operandos devem ter o mesmo tipo
     *
     * @param e
     */
    @Override
    public void visit(Diff e) {
        relationalBinOp(e, "!=");
    }

    /**
     * [a, a] → Bool, em que a ∈ {Int, Float, Char}
     * Os dois operandos devem ter o mesmo tipo
     *
     * @param e
     */
    @Override
    public void visit(Lt e) {
        relationalBinOp(e, "<");
    }

    /**
     * @param e
     */
    @Override
    public void visit(ExpItCond e) {

    }

    /**
     * inverte o sinal do operando ao qual é aplicado
     * a → a, em que a ∈ {Int, Float}
     * @param e
     */
    @Override
    public void visit(MinusExpr e) {
        SType sType = e.getSType();
        boolean isInt = sType instanceof STyInt;

        // Gera código do operando
        e.getExpr().accept(this);
        ST operand = expr;

        // Escolhe template de negação baseado no tipo
        ST aux = groupTemplate.getInstanceOf(isInt ? "ineg_expr" : "fneg_expr");
        aux.add("expr", operand);

        expr = aux;
    }

    /**
     * Bool → Bool
     * @param e
     */
    @Override
    public void visit(NotExpr e) {
        // Gera código para o operando do NOT
        e.getExpression().accept(this);
        ST operand = expr;

        // Cria template do NOT
        ST aux = groupTemplate.getInstanceOf("not_expr");
        aux.add("expr", operand);

        expr = aux;
    }

    /**
     * @param e
     */
    @Override
    public void visit(Exps e) {

    }

    /**
     * @param e
     */
    @Override
    public void visit(ArrayType e) {

    }

    /**
     * @param e
     */
    @Override
    public void visit(CallFunctionAccess e) {

    }

    /**
     * @param e
     */
    @Override
    public void visit(VarExpr e) {

    }

    /**
     * @param p
     */
    @Override
    public void visit(Param p) {

    }

    /**
     * @param varId
     */
    @Override
    public void visit(ID varId) {
        /*
            Aqui vai ser um acesso a um variável
            Tenho que pegar do store e jogar pra pilha
        */
        int index = local.get(varId.getName()).getIndex();
        SType sType = varId.getSType();

        if(sType instanceof STyInt || sType instanceof STyChar || sType instanceof STyBool) {
            expr = groupTemplate.getInstanceOf("load_int");
        }else if (sType instanceof STyFloat) {
            expr = groupTemplate.getInstanceOf("load_float");
        }

        expr.add("index", index);
    }

    /**
     * @param e
     */
    @Override
    public void visit(TypeBool e) {
        type = groupTemplate.getInstanceOf("boolean_type");
    }

    /**
     * @param e
     */
    @Override
    public void visit(TypeChar e) {
        type = groupTemplate.getInstanceOf("char_type");
    }

    /**
     * @param e
     */
    @Override
    public void visit(TypeFloat e) {
        type = groupTemplate.getInstanceOf("float_type");
    }

    /**
     * @param e
     */
    @Override
    public void visit(TypeInt e) {
        type = groupTemplate.getInstanceOf("int_type");
    }

    /**
     * @param e
     */
    @Override
    public void visit(TYID e) {

    }

    /**
     * @param e
     */
    @Override
    public void visit(LValueExp e) {

    }


    /**
     * @param e
     */
    @Override
    public void visit(IdItCond e) {

    }

    /**
     * @param e
     */
    @Override
    public void visit(IdLValue e) {

    }


    @Override
    public void visit(TrueValue e) {
        expr = groupTemplate.getInstanceOf("boolean_true");
        expr.add("value", true);
    }

    @Override
    public void visit(FalseValue e) {
        expr = groupTemplate.getInstanceOf("boolean_false");
        expr.add("value", false);
    }

    @Override
    public void visit(IntValue e) {
        expr = groupTemplate.getInstanceOf("int_expr");
        expr.add("value", e.getValue());
    }

    @Override
    public void visit(FloatValue e) {
        expr = groupTemplate.getInstanceOf("float_expr");
        expr.add("value", e.getValue());
    }

    @Override
    public void visit(CharValue e) {
        expr = groupTemplate.getInstanceOf("char_expr");
        expr.add("value", (int) e.getValue().charAt(1));

    }

    @Override
    public void visit(NullValue e) {

    }


}
