
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
     * @param b
     */
    @Override
    public void visit(Block b) {
        for (Cmd c : b.getCommands()) {
            c.accept(this);
        }
    }

    /**
     * @param p
     */
    @Override
    public void visit(CmdAssign p) {

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

    }

    /**
     * @param cmdPrint
     */
    @Override
    public void visit(CmdPrint cmdPrint) {
        cmdPrint.getExpression().accept(this);// vai jogar na variavel expr
        // SType t = cmdPrint.getExpression().getType();// TODO: verificar uma forma de pegar o tipo
//        if (t instanceof STyInt) {
//            stmt = groupTemplate.getInstanceOf("iprint");
//        }
        stmt = groupTemplate.getInstanceOf("iprint");
        stmt.add("expr", expr);
    }

    /**
     * @param p
     */
    @Override
    public void visit(CmdRead p) {

    }

    /**
     * @param cmdReturn
     */
    @Override
    public void visit(CmdReturn cmdReturn) {
        // TODO: no stg como colocar return corretamente? coloquei manualmente mas ne
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

    /**
     * @param e
     */
    @Override
    public void visit(Add e) {

    }

    /**
     * @param e
     */
    @Override
    public void visit(Sub e) {

    }

    /**
     * @param e
     */
    @Override
    public void visit(Mul e) {

    }

    /**
     * @param e
     */
    @Override
    public void visit(Div e) {

    }

    /**
     * @param e
     */
    @Override
    public void visit(Mod e) {

    }

    /**
     * @param e
     */
    @Override
    public void visit(And e) {

    }

    /**
     * @param e
     */
    @Override
    public void visit(Lt e) {

    }

    /**
     * @param e
     */
    @Override
    public void visit(Eq e) {

    }

    /**
     * @param e
     */
    @Override
    public void visit(Diff e) {

    }

    /**
     * @param e
     */
    @Override
    public void visit(ExpItCond e) {

    }

    /**
     * @param e
     */
    @Override
    public void visit(MinusExpr e) {

    }

    /**
     * @param e
     */
    @Override
    public void visit(NotExpr e) {

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
     * @param e
     */
    @Override
    public void visit(ID e) {

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
        expr = groupTemplate.getInstanceOf("float_expr");
        expr.add("value", true);
    }

    @Override
    public void visit(FalseValue e) {
        expr = groupTemplate.getInstanceOf("float_expr");
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

    }

    @Override
    public void visit(NullValue e) {

    }


}
