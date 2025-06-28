grammar Lang;

@parser::header {
    package parser;

    import ast.*;
}

@lexer::header {
    package parser;
}

// prog: (def)*;
prog returns [Program ast]
        @init {
            List<Def> defList = new ArrayList<>();
        }:
        (
            s1 = def { defList.add($s1.ast); }
        )*
        {
            $ast = new Program($start.getLine(), $start.getCharPositionInLine(), defList);
        }
;

// def: data | fun;
def returns [Def ast]
    : d1 = data { $ast = $d1.ast; }
    | f1 = fun  { $ast = $f1.ast; }
    ;

// data: 'abstract' 'data' TYID '{' (decl | fun)* '}' | 'data' TYID '{' (decl)* '}';

data
	returns[Data ast]
	@init {
		List<Node> members = new ArrayList<>();
	}:
	abs = 'abstract' 'data' TYID '{' (
		decl { members.add($decl.ast); }
		| fun { members.add($fun.ast); }
	)* '}' {
        	$ast = new AbstractData($abs.line, $abs.pos, $TYID.text, members);
    }
	| d = 'data' TYID '{' (decl { members.add($decl.ast); })* '}' {
				$ast = new Data(d.line, d.pos, $TYID.text, members);
			};

// decl: ID '::' type ';';
decl
	returns[Decl ast]:
	id = ID '::' t = type ';' {
        $ast = new Decl($id.line, $id.pos, $id.text, $t.ast);
    };

// fun: ID '(' params? ')' (':' type (',' type)*)? cmd;
fun
	returns[Fun ast]
	@init {
    	List<Type> members = new ArrayList<>();
  	}:
	fun_def = ID '(' p = params? ')' (
		':' t1 = type { members.add($t1.ast); } (
			',' t = type { members.add($t.ast); }
		)*
	)? cmd {
		$ast = new Fun($fun_def.line, $fun_def.pos, $ID.text, new Params($fun_def.line, $fun_def.pos, $p.ast), members, cmd.ast)
	};

// params: ID '::' type (',' ID '::' type)*;
params 
	returns[Params ast] 
	@init { List<Param> listParam = new ArrayList<>(); }: 
	id = ID '::' t1 = type {listParam.add(new Param($id, $t1))} 
	( ',' ID '::' t2 = type {members.add($t2.ast)} )* {
		$ast = new Params($id.line, $id.pos, members);
	} 	
	;

// type: type '['']' | btype; type: btype typeLinha;
type
	returns[Type ast]:
	type '[' ']' {$ast = new ArrayType($type.ast.getLine(), $type.ast.getCol(), $type.ast)}
	| Int = 'Int' {$ast = new TypeInt($Int.line, $Int.pos, Integer.parseInt($Int.text));}
	| Char = 'Char' {$ast = new TypeChar($Char.line, $Char.pos, $Char.text);}
	| Bool = 'Bool' {$ast = new TypeBool($Bool.line, $Bool.pos, Boolean.parseBoolean($Bool.text));}
	| Float = 'Float' {$ast = new TypeFloat($Float.line, $Float.pos, Float.parseFloat($Float.text));}
	| TYID {$ast = new TYID($TYID.line, $TYID.pos, $TYID.text);};

// block: '{' (cmd)* '}';
block
	returns[Block ast]
	@init{List<Cmd> listCmd = new ArrayList<>();}:
	'{' (
		c1 = cmd {listCmd.add($c1.ast)}
	)*  {$ast = new Block($c1.ast.getLine(), $c1.ast.getCol(), $c1.ast, listCmd); } '}';

cmd
	returns[Cmd ast]
	@init {
    	List<LValue> membersLvalue = new ArrayList<>();
		List<Expr> membersReturn = new ArrayList<>();
  	}:
	block { $ast = new Block($block.ast.getLine(), $block.ast.getCol(), $block.ast);}
	| ifCond = 'if' '(' exp ')' cmd { $ast = new CmdIf($ifCond.line, $ifCond.pos, $exp.ast, $cmd.ast);}
	| ifCond = 'if' '(' exp ')' cmd1 = cmd 'else' cmd2 = cmd { $ast = new CmdIf($ifCond.line, $ifCond.pos, $exp.ast, $cmd1.ast, $cmd2.ast );
		}
	| it = 'iterate' '(' itcond ')' cmd { $ast = new CmdIterate($it.line, $it.pos, $itcond.ast, $cmd.ast );
		}
	| rd = 'read' lvalue ';' { $ast = new CmdRead($rd.line, $rd.pos, $lvalue.ast); }
	| prt = 'print' exp ';' { $ast = new CmdPrint($prt.line, $prt.pos, $exp.ast); }
	| rt = 'return' exp { membersReturn.add($exp.ast) } (
		',' exp {membersReturn.add($exp.ast)} 
	)* { $ast = new CmdReturn($rt.line, $rt.pos, $exp.ast, membersReturn);} ';'
	| lvalue '=' exp ';' { $ast = new LvalueExp($lvalue.ast.getLine(), $lvalue.ast.getCol(), $lvalue.ast, $exp.ast);
		}
	// | ID '(' exps? ')' ('<' lvalue (',' lvalue)* '>')? ';'
	| ID '(' exps? ')' (
		'<' l1 = lvalue { membersLvalue.add($l1.ast) } (
			',' l2 = lvalue { membersLvalue.add($l2.ast) }
		)* '>' {$ast = new CmdFuncCall($ID.line, $ID.pos,$ID.text, $exps.ast, membersLvalue );
			}
	)? ';';

itcond
	returns[Expr ast]:
	ID ':' exp { $ast = new IdItCond($ID.line, $ID.pos, $ID.text, $exp.ast);
		}
	| exp { $ast = new ExpItCond($exp.ast.getLine(), $exp.ast.getCol(), $exp.ast);};

// exp: exp op exp | '!' exp | '-' exp | lvalue | '(' exp ')' | 'new' type ('[' exp ']')? | ID '('
// (exps)? ')' '[' exp ']' | 'true' | 'false' | 'null' | INT | FLOAT | CHAR;

exp
	returns[Expr ast]:
	exp1=exp op exp2=exp { $ast = new BinOP($op.ast.getLine(), $op.ast.getCol(), $exp1.ast, $op.text, $exp2.ast);
		}
	| not = '!' exp { $ast = new NotExpr($not.line, $not.pos, $exp.ast);}
	| minus = '-' exp { $ast = new MinusExpr($minus.line, $minus.pos, $exp.ast);
		}
	| lvalue { $ast = new LValue($lvalue.ast.getLine(), $lvalue.ast.getCol(), $lvalue.ast);
		}
	| '(' exp ')' { $ast = new Expr($exp.ast.getLine(), $exp.ast.getCol(), $exp.ast);
		}
	| 'new' type ('[' exp ']')? { $ast = new NewVarExpr($type.ast.getLine(), $type.ast.getCol(), $type.ast, $exp.ast);
		}
	| ID '(' (exps)? ')' '[' exp ']'  { $ast = new CallFunctionAccess($ID.line, $ID.pos, $ID.text, $exps.ast,$exp.ast);
		}
	| t = 'true'  { $ast = new TrueValue($t.line, $t.pos);}
	| f = 'false'  { $ast = new FalseValue($f.line, $f.pos);}
	| n = 'null'  { $ast = new NullValue($n.line, $n.pos);}
	| INT { $ast = new IntValue($INT.line, $INT.pos, $INT.text);}
	| FLOAT { $ast = new FloatValue($FLOAT.line, $FLOAT.pos, $FLOAT.text);}
	| CHAR { $ast = new CharValue($CHAR.line, $CHAR.pos, $CHAR.text);};


op
	returns[Operator ast]:
	andOp = '&&' {$ast = new Operator($andOp.line, $andOp.pos, $andOp.text);}
	| lessOp = '<' {$ast = new Operator($lessOp.line, $lessOp.pos, $lessOp.text);}
	| equalOp = '==' {$ast = new Operator($equalOp.line, $equalOp.pos, $equalOp.text);}
	| diffOp = '!=' {$ast = new Operator($diffOp.line, $diffOp.pos, $diffOp.text);}
	| plusOp = '+' {$ast = new Operator($plusOp.line, $plusOp.pos, $plusOp.text);}
	| minusOp = '-' {$ast = new Operator($minusOp.line, $minusOp.pos, $minusOp.text);}
	| multOp = '*' {$ast = new Operator($multOp.line, $multOp.pos, $multOp.text);}
	| divOp = '/' {$ast = new Operator($divOp.line, $divOp.pos, $divOp.text);}
	| modOp = '%' {$ast = new Operator($modOp.line, $modOp.pos, $modOp.text);};
// lvalue: ID | lvalue '[' exp ']' | lvalue '.' ID | TYID ID;

lvalue
	returns[LValue ast]:
	ID { $ast = new ID($ID.line, $ID.pos,$ID.texxt);}
	| lvalue '[' exp ']' {$ast = new LvalueExp($lvalue.ast.getLine(), $lvalue.ast.getCol(), $lvalue.ast, $exp.ast)}
	| lvalue '.' ID {$ast = new IdLvalue($lvalue.ast.getLine(), $lvalue.ast.getCol(), $lvalue.ast, $ID.text)}	
		;

exps
	returns[Exps ast]
	@init {
    	List<Expr> expsValues = new ArrayList<>();
  	}: 
	e1=exp {expsValues.add($e1.ast);}
	(',' e2=exp {expsValues.add($e2.ast);})*
		{$ast = new Exps($e1.ast.getLine(), $e1.ast.getCol(), expsValues);}
	;

//REGRAS LÉXICAS LITERAIS
INT: [0-9]+;
FLOAT: [0-9]* '.' [0-9]+;
CHAR:
	'\'' (
		~['\\] // qualquer caractere, exceto aspas simples e barra invertida
		| '\\' [ntbr'\\] // sequência de escape comum
		| '\\' DIGIT DIGIT DIGIT // sequência octal (3 dígitos)
	) '\'';

fragment DIGIT: [0-9];

// IDENTIFICADORES
ID: [a-z] [a-zA-Z0-9_]*;
TYID: [A-Z] [a-zA-Z0-9_]*;

// COMENTÁRIOS
LINE_COMMENT: '--' ~[\r\n]* -> skip;
BLOCK_COMMENT: '{-' .*? '-}' -> skip;

// IGNORAR ESPAÇOS E QUEBRAS DE LINHA
WS: [ \t\f\r\n]+ -> skip;