// Generated from ./parser/Lang.g4 by ANTLR 4.8

    package parser;

    import ast.*;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class LangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, INT=40, FLOAT=41, CHAR=42, ID=43, TYID=44, LINE_COMMENT=45, 
		BLOCK_COMMENT=46, WS=47;
	public static final int
		RULE_prog = 0, RULE_def = 1, RULE_data = 2, RULE_decl = 3, RULE_fun_abstract_data = 4, 
		RULE_fun = 5, RULE_params = 6, RULE_type = 7, RULE_btype = 8, RULE_block = 9, 
		RULE_cmd = 10, RULE_itcond = 11, RULE_exp = 12, RULE_lvalue = 13, RULE_exps = 14;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "def", "data", "decl", "fun_abstract_data", "fun", "params", 
			"type", "btype", "block", "cmd", "itcond", "exp", "lvalue", "exps"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'abstract'", "'data'", "'{'", "'}'", "'::'", "';'", "'('", "')'", 
			"':'", "','", "'['", "']'", "'Int'", "'Char'", "'Bool'", "'Float'", "'if'", 
			"'else'", "'iterate'", "'read'", "'print'", "'return'", "'='", "'<'", 
			"'>'", "'*'", "'/'", "'%'", "'+'", "'-'", "'=='", "'!='", "'&&'", "'!'", 
			"'new'", "'true'", "'false'", "'null'", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, "INT", "FLOAT", "CHAR", "ID", "TYID", "LINE_COMMENT", 
			"BLOCK_COMMENT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Lang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgContext extends ParserRuleContext {
		public Program ast;
		public DefContext s1;
		public List<DefContext> def() {
			return getRuleContexts(DefContext.class);
		}
		public DefContext def(int i) {
			return getRuleContext(DefContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);

		            List<Def> defList = new ArrayList<>();
		        
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << ID))) != 0)) {
				{
				{
				setState(30);
				((ProgContext)_localctx).s1 = def();
				 defList.add(((ProgContext)_localctx).s1.ast); 
				}
				}
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}

			            ((ProgContext)_localctx).ast =  new Program(_localctx.start.getLine(), _localctx.start.getCharPositionInLine(), defList);
			        
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefContext extends ParserRuleContext {
		public Def ast;
		public DataContext d1;
		public FunContext f1;
		public DataContext data() {
			return getRuleContext(DataContext.class,0);
		}
		public FunContext fun() {
			return getRuleContext(FunContext.class,0);
		}
		public DefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_def; }
	}

	public final DefContext def() throws RecognitionException {
		DefContext _localctx = new DefContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_def);
		try {
			setState(46);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(40);
				((DefContext)_localctx).d1 = data();
				 ((DefContext)_localctx).ast =  ((DefContext)_localctx).d1.ast; 
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(43);
				((DefContext)_localctx).f1 = fun();
				 ((DefContext)_localctx).ast =  ((DefContext)_localctx).f1.ast; 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DataContext extends ParserRuleContext {
		public Def ast;
		public Token abs;
		public Token TYID;
		public DeclContext decl;
		public Fun_abstract_dataContext fun_abstract_data;
		public Token d;
		public TerminalNode TYID() { return getToken(LangParser.TYID, 0); }
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public List<Fun_abstract_dataContext> fun_abstract_data() {
			return getRuleContexts(Fun_abstract_dataContext.class);
		}
		public Fun_abstract_dataContext fun_abstract_data(int i) {
			return getRuleContext(Fun_abstract_dataContext.class,i);
		}
		public DataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_data; }
	}

	public final DataContext data() throws RecognitionException {
		DataContext _localctx = new DataContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_data);

				List<Node> members = new ArrayList<>();
			
		int _la;
		try {
			setState(78);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(48);
				((DataContext)_localctx).abs = match(T__0);
				setState(49);
				match(T__1);
				setState(50);
				((DataContext)_localctx).TYID = match(TYID);
				setState(51);
				match(T__2);
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ID) {
					{
					setState(58);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
					case 1:
						{
						setState(52);
						((DataContext)_localctx).decl = decl();
						 members.add(((DataContext)_localctx).decl.ast); 
						}
						break;
					case 2:
						{
						setState(55);
						((DataContext)_localctx).fun_abstract_data = fun_abstract_data();
						 members.add(((DataContext)_localctx).fun_abstract_data.ast); 
						}
						break;
					}
					}
					setState(62);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(63);
				match(T__3);

				        	((DataContext)_localctx).ast =  new AbstractDataDecl((((DataContext)_localctx).abs!=null?((DataContext)_localctx).abs.getLine():0), (((DataContext)_localctx).abs!=null?((DataContext)_localctx).abs.getCharPositionInLine():0), (((DataContext)_localctx).TYID!=null?((DataContext)_localctx).TYID.getText():null), members);
				    
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(65);
				((DataContext)_localctx).d = match(T__1);
				setState(66);
				((DataContext)_localctx).TYID = match(TYID);
				setState(67);
				match(T__2);
				setState(73);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ID) {
					{
					{
					setState(68);
					((DataContext)_localctx).decl = decl();
					 members.add(((DataContext)_localctx).decl.ast); 
					}
					}
					setState(75);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(76);
				match(T__3);

								((DataContext)_localctx).ast =  new DataDecl((((DataContext)_localctx).d!=null?((DataContext)_localctx).d.getLine():0), (((DataContext)_localctx).d!=null?((DataContext)_localctx).d.getCharPositionInLine():0), (((DataContext)_localctx).TYID!=null?((DataContext)_localctx).TYID.getText():null), members);
							
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclContext extends ParserRuleContext {
		public Decl ast;
		public Token id;
		public TypeContext t;
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			((DeclContext)_localctx).id = match(ID);
			setState(81);
			match(T__4);
			setState(82);
			((DeclContext)_localctx).t = type(0);
			setState(83);
			match(T__5);

			        ((DeclContext)_localctx).ast =  new Decl((((DeclContext)_localctx).id!=null?((DeclContext)_localctx).id.getLine():0), (((DeclContext)_localctx).id!=null?((DeclContext)_localctx).id.getCharPositionInLine():0), (((DeclContext)_localctx).id!=null?((DeclContext)_localctx).id.getText():null), ((DeclContext)_localctx).t.ast);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Fun_abstract_dataContext extends ParserRuleContext {
		public FunAbstractData ast;
		public Token fun_def;
		public ParamsContext p;
		public TypeContext t1;
		public TypeContext t;
		public CmdContext cmd;
		public CmdContext cmd() {
			return getRuleContext(CmdContext.class,0);
		}
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public Fun_abstract_dataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fun_abstract_data; }
	}

	public final Fun_abstract_dataContext fun_abstract_data() throws RecognitionException {
		Fun_abstract_dataContext _localctx = new Fun_abstract_dataContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_fun_abstract_data);

		    	List<Type> members = new ArrayList<>();
		  	
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			((Fun_abstract_dataContext)_localctx).fun_def = match(ID);
			setState(87);
			match(T__6);
			setState(89);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(88);
				((Fun_abstract_dataContext)_localctx).p = params();
				}
			}

			setState(91);
			match(T__7);
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(92);
				match(T__8);
				setState(93);
				((Fun_abstract_dataContext)_localctx).t1 = type(0);
				 members.add(((Fun_abstract_dataContext)_localctx).t1.ast); 
				setState(101);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(95);
					match(T__9);
					setState(96);
					((Fun_abstract_dataContext)_localctx).t = type(0);
					 members.add(((Fun_abstract_dataContext)_localctx).t.ast); 
					}
					}
					setState(103);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(106);
			((Fun_abstract_dataContext)_localctx).cmd = cmd();

					((Fun_abstract_dataContext)_localctx).ast =  new FunAbstractData((((Fun_abstract_dataContext)_localctx).fun_def!=null?((Fun_abstract_dataContext)_localctx).fun_def.getLine():0), (((Fun_abstract_dataContext)_localctx).fun_def!=null?((Fun_abstract_dataContext)_localctx).fun_def.getCharPositionInLine():0), (((Fun_abstract_dataContext)_localctx).fun_def!=null?((Fun_abstract_dataContext)_localctx).fun_def.getText():null), ((Fun_abstract_dataContext)_localctx).p != null ? ((Fun_abstract_dataContext)_localctx).p.ast : null, members, ((Fun_abstract_dataContext)_localctx).cmd.ast);
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FunContext extends ParserRuleContext {
		public Fun ast;
		public Token fun_def;
		public ParamsContext p;
		public TypeContext t1;
		public TypeContext t;
		public CmdContext cmd;
		public CmdContext cmd() {
			return getRuleContext(CmdContext.class,0);
		}
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public FunContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fun; }
	}

	public final FunContext fun() throws RecognitionException {
		FunContext _localctx = new FunContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_fun);

		    	List<Type> members = new ArrayList<>();
		  	
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			((FunContext)_localctx).fun_def = match(ID);
			setState(110);
			match(T__6);
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(111);
				((FunContext)_localctx).p = params();
				}
			}

			setState(114);
			match(T__7);
			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(115);
				match(T__8);
				setState(116);
				((FunContext)_localctx).t1 = type(0);
				 members.add(((FunContext)_localctx).t1.ast); 
				setState(124);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(118);
					match(T__9);
					setState(119);
					((FunContext)_localctx).t = type(0);
					 members.add(((FunContext)_localctx).t.ast); 
					}
					}
					setState(126);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(129);
			((FunContext)_localctx).cmd = cmd();

					((FunContext)_localctx).ast =  new Fun((((FunContext)_localctx).fun_def!=null?((FunContext)_localctx).fun_def.getLine():0), (((FunContext)_localctx).fun_def!=null?((FunContext)_localctx).fun_def.getCharPositionInLine():0), (((FunContext)_localctx).fun_def!=null?((FunContext)_localctx).fun_def.getText():null), ((FunContext)_localctx).p != null ? ((FunContext)_localctx).p.ast : null, members, ((FunContext)_localctx).cmd.ast);
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamsContext extends ParserRuleContext {
		public Params ast;
		public Token id1;
		public TypeContext t1;
		public Token id2;
		public TypeContext t2;
		public List<TerminalNode> ID() { return getTokens(LangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(LangParser.ID, i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public ParamsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params; }
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_params);

			    List<Param> listParam = new ArrayList<>();
			
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			((ParamsContext)_localctx).id1 = match(ID);
			setState(133);
			match(T__4);
			setState(134);
			((ParamsContext)_localctx).t1 = type(0);
			listParam.add(new Param((((ParamsContext)_localctx).id1!=null?((ParamsContext)_localctx).id1.getText():null), ((ParamsContext)_localctx).t1.ast));
			setState(144);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(136);
				match(T__9);
				setState(137);
				((ParamsContext)_localctx).id2 = match(ID);
				setState(138);
				match(T__4);
				setState(139);
				((ParamsContext)_localctx).t2 = type(0);
				listParam.add(new Param((((ParamsContext)_localctx).id2!=null?((ParamsContext)_localctx).id2.getText():null), ((ParamsContext)_localctx).t2.ast));
				}
				}
				setState(146);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}

					((ParamsContext)_localctx).ast =  new Params((((ParamsContext)_localctx).id1!=null?((ParamsContext)_localctx).id1.getLine():0), (((ParamsContext)_localctx).id1!=null?((ParamsContext)_localctx).id1.getCharPositionInLine():0), listParam);
				
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public Type ast;
		public TypeContext t1;
		public BtypeContext b;
		public BtypeContext btype() {
			return getRuleContext(BtypeContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		return type(0);
	}

	private TypeContext type(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeContext _localctx = new TypeContext(_ctx, _parentState);
		TypeContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_type, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(150);
			((TypeContext)_localctx).b = btype();
			((TypeContext)_localctx).ast =  ((TypeContext)_localctx).b.ast;
			}
			_ctx.stop = _input.LT(-1);
			setState(159);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new TypeContext(_parentctx, _parentState);
					_localctx.t1 = _prevctx;
					_localctx.t1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_type);
					setState(153);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(154);
					match(T__10);
					setState(155);
					match(T__11);
					((TypeContext)_localctx).ast =  new ArrayType(((TypeContext)_localctx).t1.ast.getLine(), ((TypeContext)_localctx).t1.ast.getCol());
					}
					} 
				}
				setState(161);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class BtypeContext extends ParserRuleContext {
		public BType ast;
		public Token Int1;
		public Token Char1;
		public Token Bool1;
		public Token Float1;
		public Token TYID;
		public TerminalNode TYID() { return getToken(LangParser.TYID, 0); }
		public BtypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_btype; }
	}

	public final BtypeContext btype() throws RecognitionException {
		BtypeContext _localctx = new BtypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_btype);
		try {
			setState(172);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__12:
				enterOuterAlt(_localctx, 1);
				{
				setState(162);
				((BtypeContext)_localctx).Int1 = match(T__12);
				((BtypeContext)_localctx).ast =  new TypeInt((((BtypeContext)_localctx).Int1!=null?((BtypeContext)_localctx).Int1.getLine():0), (((BtypeContext)_localctx).Int1!=null?((BtypeContext)_localctx).Int1.getCharPositionInLine():0));
				}
				break;
			case T__13:
				enterOuterAlt(_localctx, 2);
				{
				setState(164);
				((BtypeContext)_localctx).Char1 = match(T__13);
				((BtypeContext)_localctx).ast =  new TypeChar((((BtypeContext)_localctx).Char1!=null?((BtypeContext)_localctx).Char1.getLine():0), (((BtypeContext)_localctx).Char1!=null?((BtypeContext)_localctx).Char1.getCharPositionInLine():0));
				}
				break;
			case T__14:
				enterOuterAlt(_localctx, 3);
				{
				setState(166);
				((BtypeContext)_localctx).Bool1 = match(T__14);
				((BtypeContext)_localctx).ast =  new TypeBool((((BtypeContext)_localctx).Bool1!=null?((BtypeContext)_localctx).Bool1.getLine():0), (((BtypeContext)_localctx).Bool1!=null?((BtypeContext)_localctx).Bool1.getCharPositionInLine():0));
				}
				break;
			case T__15:
				enterOuterAlt(_localctx, 4);
				{
				setState(168);
				((BtypeContext)_localctx).Float1 = match(T__15);
				((BtypeContext)_localctx).ast =  new TypeFloat((((BtypeContext)_localctx).Float1!=null?((BtypeContext)_localctx).Float1.getLine():0), (((BtypeContext)_localctx).Float1!=null?((BtypeContext)_localctx).Float1.getCharPositionInLine():0));
				}
				break;
			case TYID:
				enterOuterAlt(_localctx, 5);
				{
				setState(170);
				((BtypeContext)_localctx).TYID = match(TYID);
				((BtypeContext)_localctx).ast =  new TYID((((BtypeContext)_localctx).TYID!=null?((BtypeContext)_localctx).TYID.getLine():0), (((BtypeContext)_localctx).TYID!=null?((BtypeContext)_localctx).TYID.getCharPositionInLine():0), (((BtypeContext)_localctx).TYID!=null?((BtypeContext)_localctx).TYID.getText():null));
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public Block ast;
		public CmdContext c1;
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_block);
		 List<Cmd> listCmd = new ArrayList<>(); int line = -1; int col = -1; 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			match(T__2);
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__16) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << ID))) != 0)) {
				{
				{
				setState(175);
				((BlockContext)_localctx).c1 = cmd();

				                if (line == -1) { line = ((BlockContext)_localctx).c1.ast.getLine(); col = ((BlockContext)_localctx).c1.ast.getCol(); }
				                listCmd.add(((BlockContext)_localctx).c1.ast);
				            
				}
				}
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(183);
			match(T__3);

			        ((BlockContext)_localctx).ast =  new Block(line, col, listCmd);
			    
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CmdContext extends ParserRuleContext {
		public Cmd ast;
		public BlockContext block;
		public Token ifCond;
		public ExpContext exp;
		public CmdContext cmd;
		public CmdContext cmd1;
		public CmdContext cmd2;
		public Token it;
		public ItcondContext itcond;
		public Token rd;
		public LvalueContext lvalue;
		public Token prt;
		public Token rt;
		public Token ID;
		public ExpsContext exps;
		public LvalueContext l1;
		public LvalueContext l2;
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public ItcondContext itcond() {
			return getRuleContext(ItcondContext.class,0);
		}
		public List<LvalueContext> lvalue() {
			return getRuleContexts(LvalueContext.class);
		}
		public LvalueContext lvalue(int i) {
			return getRuleContext(LvalueContext.class,i);
		}
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public ExpsContext exps() {
			return getRuleContext(ExpsContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cmd);

		    	List<LValue> membersLValue = new ArrayList<>();
				List<Expr> membersReturn = new ArrayList<>();
		  	
		int _la;
		try {
			setState(267);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(186);
				((CmdContext)_localctx).block = block();
				 ((CmdContext)_localctx).ast =  ((CmdContext)_localctx).block.ast;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(189);
				((CmdContext)_localctx).ifCond = match(T__16);
				setState(190);
				match(T__6);
				setState(191);
				((CmdContext)_localctx).exp = exp(0);
				setState(192);
				match(T__7);
				setState(193);
				((CmdContext)_localctx).cmd = cmd();
				 ((CmdContext)_localctx).ast =  new CmdIf((((CmdContext)_localctx).ifCond!=null?((CmdContext)_localctx).ifCond.getLine():0), (((CmdContext)_localctx).ifCond!=null?((CmdContext)_localctx).ifCond.getCharPositionInLine():0), ((CmdContext)_localctx).exp.ast, ((CmdContext)_localctx).cmd.ast);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(196);
				((CmdContext)_localctx).ifCond = match(T__16);
				setState(197);
				match(T__6);
				setState(198);
				((CmdContext)_localctx).exp = exp(0);
				setState(199);
				match(T__7);
				setState(200);
				((CmdContext)_localctx).cmd1 = cmd();
				setState(201);
				match(T__17);
				setState(202);
				((CmdContext)_localctx).cmd2 = cmd();
				 ((CmdContext)_localctx).ast =  new CmdIf((((CmdContext)_localctx).ifCond!=null?((CmdContext)_localctx).ifCond.getLine():0), (((CmdContext)_localctx).ifCond!=null?((CmdContext)_localctx).ifCond.getCharPositionInLine():0), ((CmdContext)_localctx).exp.ast, ((CmdContext)_localctx).cmd1.ast, ((CmdContext)_localctx).cmd2.ast );
						
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(205);
				((CmdContext)_localctx).it = match(T__18);
				setState(206);
				match(T__6);
				setState(207);
				((CmdContext)_localctx).itcond = itcond();
				setState(208);
				match(T__7);
				setState(209);
				((CmdContext)_localctx).cmd = cmd();
				 ((CmdContext)_localctx).ast =  new CmdIterate((((CmdContext)_localctx).it!=null?((CmdContext)_localctx).it.getLine():0), (((CmdContext)_localctx).it!=null?((CmdContext)_localctx).it.getCharPositionInLine():0), ((CmdContext)_localctx).itcond.ast, ((CmdContext)_localctx).cmd.ast );
						
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(212);
				((CmdContext)_localctx).rd = match(T__19);
				setState(213);
				((CmdContext)_localctx).lvalue = lvalue(0);
				setState(214);
				match(T__5);
				 ((CmdContext)_localctx).ast =  new CmdRead((((CmdContext)_localctx).rd!=null?((CmdContext)_localctx).rd.getLine():0), (((CmdContext)_localctx).rd!=null?((CmdContext)_localctx).rd.getCharPositionInLine():0), ((CmdContext)_localctx).lvalue.ast); 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(217);
				((CmdContext)_localctx).prt = match(T__20);
				setState(218);
				((CmdContext)_localctx).exp = exp(0);
				setState(219);
				match(T__5);
				 ((CmdContext)_localctx).ast =  new CmdPrint((((CmdContext)_localctx).prt!=null?((CmdContext)_localctx).prt.getLine():0), (((CmdContext)_localctx).prt!=null?((CmdContext)_localctx).prt.getCharPositionInLine():0), ((CmdContext)_localctx).exp.ast); 
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(222);
				((CmdContext)_localctx).rt = match(T__21);
				setState(223);
				((CmdContext)_localctx).exp = exp(0);
				 membersReturn.add(((CmdContext)_localctx).exp.ast) ;
				setState(231);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(225);
					match(T__9);
					setState(226);
					((CmdContext)_localctx).exp = exp(0);
					membersReturn.add(((CmdContext)_localctx).exp.ast);
					}
					}
					setState(233);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				 ((CmdContext)_localctx).ast =  new CmdReturn((((CmdContext)_localctx).rt!=null?((CmdContext)_localctx).rt.getLine():0), (((CmdContext)_localctx).rt!=null?((CmdContext)_localctx).rt.getCharPositionInLine():0), membersReturn);
				setState(235);
				match(T__5);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(237);
				((CmdContext)_localctx).lvalue = lvalue(0);
				setState(238);
				match(T__22);
				setState(239);
				((CmdContext)_localctx).exp = exp(0);
				setState(240);
				match(T__5);
				 ((CmdContext)_localctx).ast =  new CmdAssign(((CmdContext)_localctx).lvalue.ast.getLine(), ((CmdContext)_localctx).lvalue.ast.getCol(), ((CmdContext)_localctx).lvalue.ast, ((CmdContext)_localctx).exp.ast);
						
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(243);
				((CmdContext)_localctx).ID = match(ID);
				setState(244);
				match(T__6);
				setState(246);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__29) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << INT) | (1L << FLOAT) | (1L << CHAR) | (1L << ID))) != 0)) {
					{
					setState(245);
					((CmdContext)_localctx).exps = exps();
					}
				}

				setState(248);
				match(T__7);
				setState(263);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__23) {
					{
					setState(249);
					match(T__23);
					setState(250);
					((CmdContext)_localctx).l1 = lvalue(0);
					 membersLValue.add(((CmdContext)_localctx).l1.ast) ;
					setState(258);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__9) {
						{
						{
						setState(252);
						match(T__9);
						setState(253);
						((CmdContext)_localctx).l2 = lvalue(0);
						 membersLValue.add(((CmdContext)_localctx).l2.ast) ;
						}
						}
						setState(260);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(261);
					match(T__24);
					}
				}

				((CmdContext)_localctx).ast =  new CmdFuncCall((((CmdContext)_localctx).ID!=null?((CmdContext)_localctx).ID.getLine():0), (((CmdContext)_localctx).ID!=null?((CmdContext)_localctx).ID.getCharPositionInLine():0),(((CmdContext)_localctx).ID!=null?((CmdContext)_localctx).ID.getText():null), ((CmdContext)_localctx).exps != null ? ((CmdContext)_localctx).exps.ast : null, membersLValue );
				setState(266);
				match(T__5);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ItcondContext extends ParserRuleContext {
		public Itcond ast;
		public Token ID;
		public ExpContext exp;
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public ItcondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_itcond; }
	}

	public final ItcondContext itcond() throws RecognitionException {
		ItcondContext _localctx = new ItcondContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_itcond);
		try {
			setState(277);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(269);
				((ItcondContext)_localctx).ID = match(ID);
				setState(270);
				match(T__8);
				setState(271);
				((ItcondContext)_localctx).exp = exp(0);
				 ((ItcondContext)_localctx).ast =  new IdItCond((((ItcondContext)_localctx).ID!=null?((ItcondContext)_localctx).ID.getLine():0), (((ItcondContext)_localctx).ID!=null?((ItcondContext)_localctx).ID.getCharPositionInLine():0), (((ItcondContext)_localctx).ID!=null?((ItcondContext)_localctx).ID.getText():null), ((ItcondContext)_localctx).exp.ast);
						
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(274);
				((ItcondContext)_localctx).exp = exp(0);
				 ((ItcondContext)_localctx).ast =  new ExpItCond(((ItcondContext)_localctx).exp.ast.getLine(), ((ItcondContext)_localctx).exp.ast.getCol(), ((ItcondContext)_localctx).exp.ast);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpContext extends ParserRuleContext {
		public Expr ast;
		public ExpContext exp1;
		public Token not;
		public ExpContext exp;
		public Token minus;
		public LvalueContext lvalue;
		public TypeContext type;
		public ExpContext e;
		public BtypeContext btype;
		public Token ID;
		public ExpsContext exps;
		public Token t;
		public Token f;
		public Token n;
		public Token INT;
		public Token FLOAT;
		public Token CHAR;
		public Token op;
		public ExpContext exp2;
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public BtypeContext btype() {
			return getRuleContext(BtypeContext.class,0);
		}
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public ExpsContext exps() {
			return getRuleContext(ExpsContext.class,0);
		}
		public TerminalNode INT() { return getToken(LangParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(LangParser.FLOAT, 0); }
		public TerminalNode CHAR() { return getToken(LangParser.CHAR, 0); }
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
	}

	public final ExpContext exp() throws RecognitionException {
		return exp(0);
	}

	private ExpContext exp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpContext _localctx = new ExpContext(_ctx, _parentState);
		ExpContext _prevctx = _localctx;
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_exp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(331);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(280);
				((ExpContext)_localctx).not = match(T__33);
				setState(281);
				((ExpContext)_localctx).exp = exp(13);
				 ((ExpContext)_localctx).ast =  new NotExpr((((ExpContext)_localctx).not!=null?((ExpContext)_localctx).not.getLine():0), (((ExpContext)_localctx).not!=null?((ExpContext)_localctx).not.getCharPositionInLine():0), ((ExpContext)_localctx).exp.ast);
				}
				break;
			case 2:
				{
				setState(284);
				((ExpContext)_localctx).minus = match(T__29);
				setState(285);
				((ExpContext)_localctx).exp = exp(12);
				 ((ExpContext)_localctx).ast =  new MinusExpr((((ExpContext)_localctx).minus!=null?((ExpContext)_localctx).minus.getLine():0), (((ExpContext)_localctx).minus!=null?((ExpContext)_localctx).minus.getCharPositionInLine():0), ((ExpContext)_localctx).exp.ast);
				}
				break;
			case 3:
				{
				setState(288);
				((ExpContext)_localctx).lvalue = lvalue(0);
				 ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).lvalue.ast;
						
				}
				break;
			case 4:
				{
				setState(291);
				match(T__6);
				setState(292);
				((ExpContext)_localctx).exp = exp(0);
				setState(293);
				match(T__7);
				 ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).exp.ast; 
				}
				break;
			case 5:
				{
				setState(296);
				match(T__34);
				setState(297);
				((ExpContext)_localctx).type = type(0);
				{
				setState(298);
				match(T__10);
				setState(299);
				((ExpContext)_localctx).e = ((ExpContext)_localctx).exp = exp(0);
				setState(300);
				match(T__11);
				}
				 ((ExpContext)_localctx).ast =  new VarExpr(((ExpContext)_localctx).type.ast.getLine(), ((ExpContext)_localctx).type.ast.getCol(), ((ExpContext)_localctx).type.ast, ((ExpContext)_localctx).e != null ? ((ExpContext)_localctx).e.ast : null);
				}
				break;
			case 6:
				{
				setState(304);
				match(T__34);
				setState(305);
				((ExpContext)_localctx).btype = btype();
				 ((ExpContext)_localctx).ast =  new VarExpr(((ExpContext)_localctx).btype.ast.getLine(), ((ExpContext)_localctx).btype.ast.getCol(), ((ExpContext)_localctx).btype.ast, ((ExpContext)_localctx).e != null ? ((ExpContext)_localctx).e.ast : null);
				}
				break;
			case 7:
				{
				setState(308);
				((ExpContext)_localctx).ID = match(ID);
				setState(309);
				match(T__6);
				setState(311);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__29) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << INT) | (1L << FLOAT) | (1L << CHAR) | (1L << ID))) != 0)) {
					{
					setState(310);
					((ExpContext)_localctx).exps = exps();
					}
				}

				setState(313);
				match(T__7);
				setState(314);
				match(T__10);
				setState(315);
				((ExpContext)_localctx).exp = exp(0);
				setState(316);
				match(T__11);
				 ((ExpContext)_localctx).ast =  new CallFunctionAccess((((ExpContext)_localctx).ID!=null?((ExpContext)_localctx).ID.getLine():0), (((ExpContext)_localctx).ID!=null?((ExpContext)_localctx).ID.getCharPositionInLine():0), (((ExpContext)_localctx).ID!=null?((ExpContext)_localctx).ID.getText():null), ((ExpContext)_localctx).exps !=null ? ((ExpContext)_localctx).exps.ast : null , ((ExpContext)_localctx).exp.ast);
						
				}
				break;
			case 8:
				{
				setState(319);
				((ExpContext)_localctx).t = match(T__35);
				 ((ExpContext)_localctx).ast =  new TrueValue((((ExpContext)_localctx).t!=null?((ExpContext)_localctx).t.getLine():0), (((ExpContext)_localctx).t!=null?((ExpContext)_localctx).t.getCharPositionInLine():0));
				}
				break;
			case 9:
				{
				setState(321);
				((ExpContext)_localctx).f = match(T__36);
				 ((ExpContext)_localctx).ast =  new FalseValue((((ExpContext)_localctx).f!=null?((ExpContext)_localctx).f.getLine():0), (((ExpContext)_localctx).f!=null?((ExpContext)_localctx).f.getCharPositionInLine():0));
				}
				break;
			case 10:
				{
				setState(323);
				((ExpContext)_localctx).n = match(T__37);
				 ((ExpContext)_localctx).ast =  new NullValue((((ExpContext)_localctx).n!=null?((ExpContext)_localctx).n.getLine():0), (((ExpContext)_localctx).n!=null?((ExpContext)_localctx).n.getCharPositionInLine():0));
				}
				break;
			case 11:
				{
				setState(325);
				((ExpContext)_localctx).INT = match(INT);
				 ((ExpContext)_localctx).ast =  new IntValue((((ExpContext)_localctx).INT!=null?((ExpContext)_localctx).INT.getLine():0), (((ExpContext)_localctx).INT!=null?((ExpContext)_localctx).INT.getCharPositionInLine():0), (((ExpContext)_localctx).INT!=null?((ExpContext)_localctx).INT.getText():null));
				}
				break;
			case 12:
				{
				setState(327);
				((ExpContext)_localctx).FLOAT = match(FLOAT);
				 ((ExpContext)_localctx).ast =  new FloatValue((((ExpContext)_localctx).FLOAT!=null?((ExpContext)_localctx).FLOAT.getLine():0), (((ExpContext)_localctx).FLOAT!=null?((ExpContext)_localctx).FLOAT.getCharPositionInLine():0), (((ExpContext)_localctx).FLOAT!=null?((ExpContext)_localctx).FLOAT.getText():null));
				}
				break;
			case 13:
				{
				setState(329);
				((ExpContext)_localctx).CHAR = match(CHAR);
				 ((ExpContext)_localctx).ast =  new CharValue((((ExpContext)_localctx).CHAR!=null?((ExpContext)_localctx).CHAR.getLine():0), (((ExpContext)_localctx).CHAR!=null?((ExpContext)_localctx).CHAR.getCharPositionInLine():0), (((ExpContext)_localctx).CHAR!=null?((ExpContext)_localctx).CHAR.getText():null));
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(380);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(378);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
					case 1:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						_localctx.exp1 = _prevctx;
						_localctx.exp1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(333);
						if (!(precpred(_ctx, 22))) throw new FailedPredicateException(this, "precpred(_ctx, 22)");
						setState(334);
						((ExpContext)_localctx).op = match(T__25);
						setState(335);
						((ExpContext)_localctx).exp2 = ((ExpContext)_localctx).exp = exp(23);
						 ((ExpContext)_localctx).ast =  new Mul(((ExpContext)_localctx).exp1.ast.getLine(), ((ExpContext)_localctx).exp1.ast.getCol(), ((ExpContext)_localctx).exp1.ast, ((ExpContext)_localctx).exp2.ast);
						}
						break;
					case 2:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						_localctx.exp1 = _prevctx;
						_localctx.exp1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(338);
						if (!(precpred(_ctx, 21))) throw new FailedPredicateException(this, "precpred(_ctx, 21)");
						setState(339);
						((ExpContext)_localctx).op = match(T__26);
						setState(340);
						((ExpContext)_localctx).exp2 = ((ExpContext)_localctx).exp = exp(22);
						 ((ExpContext)_localctx).ast =  new Div(((ExpContext)_localctx).exp1.ast.getLine(), ((ExpContext)_localctx).exp1.ast.getCol(), ((ExpContext)_localctx).exp1.ast, ((ExpContext)_localctx).exp2.ast);
						}
						break;
					case 3:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						_localctx.exp1 = _prevctx;
						_localctx.exp1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(343);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(344);
						((ExpContext)_localctx).op = match(T__27);
						setState(345);
						((ExpContext)_localctx).exp2 = ((ExpContext)_localctx).exp = exp(21);
						 ((ExpContext)_localctx).ast =  new Mod(((ExpContext)_localctx).exp1.ast.getLine(), ((ExpContext)_localctx).exp1.ast.getCol(), ((ExpContext)_localctx).exp1.ast, ((ExpContext)_localctx).exp2.ast);
						}
						break;
					case 4:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						_localctx.exp1 = _prevctx;
						_localctx.exp1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(348);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(349);
						((ExpContext)_localctx).op = match(T__28);
						setState(350);
						((ExpContext)_localctx).exp2 = ((ExpContext)_localctx).exp = exp(20);
						 ((ExpContext)_localctx).ast =  new Add(((ExpContext)_localctx).exp1.ast.getLine(), ((ExpContext)_localctx).exp1.ast.getCol(), ((ExpContext)_localctx).exp1.ast, ((ExpContext)_localctx).exp2.ast);
						}
						break;
					case 5:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						_localctx.exp1 = _prevctx;
						_localctx.exp1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(353);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(354);
						((ExpContext)_localctx).op = match(T__29);
						setState(355);
						((ExpContext)_localctx).exp2 = ((ExpContext)_localctx).exp = exp(19);
						 ((ExpContext)_localctx).ast =  new Sub(((ExpContext)_localctx).exp1.ast.getLine(), ((ExpContext)_localctx).exp1.ast.getCol(), ((ExpContext)_localctx).exp1.ast, ((ExpContext)_localctx).exp2.ast);
						}
						break;
					case 6:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						_localctx.exp1 = _prevctx;
						_localctx.exp1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(358);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(359);
						((ExpContext)_localctx).op = match(T__23);
						setState(360);
						((ExpContext)_localctx).exp2 = ((ExpContext)_localctx).exp = exp(18);
						 ((ExpContext)_localctx).ast =  new Lt(((ExpContext)_localctx).exp1.ast.getLine(), ((ExpContext)_localctx).exp1.ast.getCol(), ((ExpContext)_localctx).exp1.ast, ((ExpContext)_localctx).exp2.ast);
						}
						break;
					case 7:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						_localctx.exp1 = _prevctx;
						_localctx.exp1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(363);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(364);
						((ExpContext)_localctx).op = match(T__30);
						setState(365);
						((ExpContext)_localctx).exp2 = ((ExpContext)_localctx).exp = exp(17);
						 ((ExpContext)_localctx).ast =  new Eq(((ExpContext)_localctx).exp1.ast.getLine(), ((ExpContext)_localctx).exp1.ast.getCol(), ((ExpContext)_localctx).exp1.ast, ((ExpContext)_localctx).exp2.ast);
						}
						break;
					case 8:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						_localctx.exp1 = _prevctx;
						_localctx.exp1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(368);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(369);
						((ExpContext)_localctx).op = match(T__31);
						setState(370);
						((ExpContext)_localctx).exp2 = ((ExpContext)_localctx).exp = exp(16);
						 ((ExpContext)_localctx).ast =  new Diff(((ExpContext)_localctx).exp1.ast.getLine(), ((ExpContext)_localctx).exp1.ast.getCol(), ((ExpContext)_localctx).exp1.ast, ((ExpContext)_localctx).exp2.ast);
						}
						break;
					case 9:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						_localctx.exp1 = _prevctx;
						_localctx.exp1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(373);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(374);
						((ExpContext)_localctx).op = match(T__32);
						setState(375);
						((ExpContext)_localctx).exp2 = ((ExpContext)_localctx).exp = exp(15);
						 ((ExpContext)_localctx).ast =  new And(((ExpContext)_localctx).exp1.ast.getLine(), ((ExpContext)_localctx).exp1.ast.getCol(), ((ExpContext)_localctx).exp1.ast, ((ExpContext)_localctx).exp2.ast);
						}
						break;
					}
					} 
				}
				setState(382);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class LvalueContext extends ParserRuleContext {
		public LValue ast;
		public LvalueContext l1;
		public Token ID;
		public ExpContext exp;
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public LvalueContext lvalue() {
			return getRuleContext(LvalueContext.class,0);
		}
		public LvalueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lvalue; }
	}

	public final LvalueContext lvalue() throws RecognitionException {
		return lvalue(0);
	}

	private LvalueContext lvalue(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		LvalueContext _localctx = new LvalueContext(_ctx, _parentState);
		LvalueContext _prevctx = _localctx;
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_lvalue, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(384);
			((LvalueContext)_localctx).ID = match(ID);
			 ((LvalueContext)_localctx).ast =  new ID((((LvalueContext)_localctx).ID!=null?((LvalueContext)_localctx).ID.getLine():0), (((LvalueContext)_localctx).ID!=null?((LvalueContext)_localctx).ID.getCharPositionInLine():0), (((LvalueContext)_localctx).ID!=null?((LvalueContext)_localctx).ID.getText():null));
			}
			_ctx.stop = _input.LT(-1);
			setState(399);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(397);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
					case 1:
						{
						_localctx = new LvalueContext(_parentctx, _parentState);
						_localctx.l1 = _prevctx;
						_localctx.l1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_lvalue);
						setState(387);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(388);
						match(T__10);
						setState(389);
						((LvalueContext)_localctx).exp = exp(0);
						setState(390);
						match(T__11);
						((LvalueContext)_localctx).ast =  new LValueExp(((LvalueContext)_localctx).l1.ast.getLine(), ((LvalueContext)_localctx).l1.ast.getCol(), ((LvalueContext)_localctx).l1.ast, ((LvalueContext)_localctx).exp.ast);
						}
						break;
					case 2:
						{
						_localctx = new LvalueContext(_parentctx, _parentState);
						_localctx.l1 = _prevctx;
						_localctx.l1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_lvalue);
						setState(393);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(394);
						match(T__38);
						setState(395);
						((LvalueContext)_localctx).ID = match(ID);
						((LvalueContext)_localctx).ast =  new IdLValue(((LvalueContext)_localctx).l1.ast.getLine(), ((LvalueContext)_localctx).l1.ast.getCol(), ((LvalueContext)_localctx).l1.ast, (((LvalueContext)_localctx).ID!=null?((LvalueContext)_localctx).ID.getText():null));
						}
						break;
					}
					} 
				}
				setState(401);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ExpsContext extends ParserRuleContext {
		public Exps ast;
		public ExpContext e1;
		public ExpContext e2;
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public ExpsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exps; }
	}

	public final ExpsContext exps() throws RecognitionException {
		ExpsContext _localctx = new ExpsContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_exps);

		    	List<Expr> expsValues = new ArrayList<>();
		  	
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(402);
			((ExpsContext)_localctx).e1 = exp(0);
			expsValues.add(((ExpsContext)_localctx).e1.ast);
			setState(410);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(404);
				match(T__9);
				setState(405);
				((ExpsContext)_localctx).e2 = exp(0);
				expsValues.add(((ExpsContext)_localctx).e2.ast);
				}
				}
				setState(412);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			((ExpsContext)_localctx).ast =  new Exps(((ExpsContext)_localctx).e1.ast.getLine(), ((ExpsContext)_localctx).e1.ast.getCol(), expsValues);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 7:
			return type_sempred((TypeContext)_localctx, predIndex);
		case 12:
			return exp_sempred((ExpContext)_localctx, predIndex);
		case 13:
			return lvalue_sempred((LvalueContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean type_sempred(TypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean exp_sempred(ExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 22);
		case 2:
			return precpred(_ctx, 21);
		case 3:
			return precpred(_ctx, 20);
		case 4:
			return precpred(_ctx, 19);
		case 5:
			return precpred(_ctx, 18);
		case 6:
			return precpred(_ctx, 17);
		case 7:
			return precpred(_ctx, 16);
		case 8:
			return precpred(_ctx, 15);
		case 9:
			return precpred(_ctx, 14);
		}
		return true;
	}
	private boolean lvalue_sempred(LvalueContext _localctx, int predIndex) {
		switch (predIndex) {
		case 10:
			return precpred(_ctx, 2);
		case 11:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\61\u01a2\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\7\2$\n"+
		"\2\f\2\16\2\'\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\5\3\61\n\3\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4=\n\4\f\4\16\4@\13\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\7\4J\n\4\f\4\16\4M\13\4\3\4\3\4\5\4Q\n\4\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\6\3\6\3\6\5\6\\\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7"+
		"\6f\n\6\f\6\16\6i\13\6\5\6k\n\6\3\6\3\6\3\6\3\7\3\7\3\7\5\7s\n\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7}\n\7\f\7\16\7\u0080\13\7\5\7\u0082\n\7"+
		"\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u0091\n\b\f\b"+
		"\16\b\u0094\13\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u00a0\n\t"+
		"\f\t\16\t\u00a3\13\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00af"+
		"\n\n\3\13\3\13\3\13\3\13\7\13\u00b5\n\13\f\13\16\13\u00b8\13\13\3\13\3"+
		"\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u00e8\n\f\f\f\16\f\u00eb"+
		"\13\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00f9\n\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u0103\n\f\f\f\16\f\u0106\13\f\3\f\3"+
		"\f\5\f\u010a\n\f\3\f\3\f\5\f\u010e\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\5\r\u0118\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u013a\n\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\5\16\u014e\n\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u017d\n\16\f\16\16\16\u0180\13\16"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\7\17\u0190\n\17\f\17\16\17\u0193\13\17\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\7\20\u019b\n\20\f\20\16\20\u019e\13\20\3\20\3\20\3\20\2\5\20\32\34\21"+
		"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36\2\2\2\u01cb\2%\3\2\2\2\4\60\3"+
		"\2\2\2\6P\3\2\2\2\bR\3\2\2\2\nX\3\2\2\2\fo\3\2\2\2\16\u0086\3\2\2\2\20"+
		"\u0097\3\2\2\2\22\u00ae\3\2\2\2\24\u00b0\3\2\2\2\26\u010d\3\2\2\2\30\u0117"+
		"\3\2\2\2\32\u014d\3\2\2\2\34\u0181\3\2\2\2\36\u0194\3\2\2\2 !\5\4\3\2"+
		"!\"\b\2\1\2\"$\3\2\2\2# \3\2\2\2$\'\3\2\2\2%#\3\2\2\2%&\3\2\2\2&(\3\2"+
		"\2\2\'%\3\2\2\2()\b\2\1\2)\3\3\2\2\2*+\5\6\4\2+,\b\3\1\2,\61\3\2\2\2-"+
		".\5\f\7\2./\b\3\1\2/\61\3\2\2\2\60*\3\2\2\2\60-\3\2\2\2\61\5\3\2\2\2\62"+
		"\63\7\3\2\2\63\64\7\4\2\2\64\65\7.\2\2\65>\7\5\2\2\66\67\5\b\5\2\678\b"+
		"\4\1\28=\3\2\2\29:\5\n\6\2:;\b\4\1\2;=\3\2\2\2<\66\3\2\2\2<9\3\2\2\2="+
		"@\3\2\2\2><\3\2\2\2>?\3\2\2\2?A\3\2\2\2@>\3\2\2\2AB\7\6\2\2BQ\b\4\1\2"+
		"CD\7\4\2\2DE\7.\2\2EK\7\5\2\2FG\5\b\5\2GH\b\4\1\2HJ\3\2\2\2IF\3\2\2\2"+
		"JM\3\2\2\2KI\3\2\2\2KL\3\2\2\2LN\3\2\2\2MK\3\2\2\2NO\7\6\2\2OQ\b\4\1\2"+
		"P\62\3\2\2\2PC\3\2\2\2Q\7\3\2\2\2RS\7-\2\2ST\7\7\2\2TU\5\20\t\2UV\7\b"+
		"\2\2VW\b\5\1\2W\t\3\2\2\2XY\7-\2\2Y[\7\t\2\2Z\\\5\16\b\2[Z\3\2\2\2[\\"+
		"\3\2\2\2\\]\3\2\2\2]j\7\n\2\2^_\7\13\2\2_`\5\20\t\2`g\b\6\1\2ab\7\f\2"+
		"\2bc\5\20\t\2cd\b\6\1\2df\3\2\2\2ea\3\2\2\2fi\3\2\2\2ge\3\2\2\2gh\3\2"+
		"\2\2hk\3\2\2\2ig\3\2\2\2j^\3\2\2\2jk\3\2\2\2kl\3\2\2\2lm\5\26\f\2mn\b"+
		"\6\1\2n\13\3\2\2\2op\7-\2\2pr\7\t\2\2qs\5\16\b\2rq\3\2\2\2rs\3\2\2\2s"+
		"t\3\2\2\2t\u0081\7\n\2\2uv\7\13\2\2vw\5\20\t\2w~\b\7\1\2xy\7\f\2\2yz\5"+
		"\20\t\2z{\b\7\1\2{}\3\2\2\2|x\3\2\2\2}\u0080\3\2\2\2~|\3\2\2\2~\177\3"+
		"\2\2\2\177\u0082\3\2\2\2\u0080~\3\2\2\2\u0081u\3\2\2\2\u0081\u0082\3\2"+
		"\2\2\u0082\u0083\3\2\2\2\u0083\u0084\5\26\f\2\u0084\u0085\b\7\1\2\u0085"+
		"\r\3\2\2\2\u0086\u0087\7-\2\2\u0087\u0088\7\7\2\2\u0088\u0089\5\20\t\2"+
		"\u0089\u0092\b\b\1\2\u008a\u008b\7\f\2\2\u008b\u008c\7-\2\2\u008c\u008d"+
		"\7\7\2\2\u008d\u008e\5\20\t\2\u008e\u008f\b\b\1\2\u008f\u0091\3\2\2\2"+
		"\u0090\u008a\3\2\2\2\u0091\u0094\3\2\2\2\u0092\u0090\3\2\2\2\u0092\u0093"+
		"\3\2\2\2\u0093\u0095\3\2\2\2\u0094\u0092\3\2\2\2\u0095\u0096\b\b\1\2\u0096"+
		"\17\3\2\2\2\u0097\u0098\b\t\1\2\u0098\u0099\5\22\n\2\u0099\u009a\b\t\1"+
		"\2\u009a\u00a1\3\2\2\2\u009b\u009c\f\4\2\2\u009c\u009d\7\r\2\2\u009d\u009e"+
		"\7\16\2\2\u009e\u00a0\b\t\1\2\u009f\u009b\3\2\2\2\u00a0\u00a3\3\2\2\2"+
		"\u00a1\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\21\3\2\2\2\u00a3\u00a1"+
		"\3\2\2\2\u00a4\u00a5\7\17\2\2\u00a5\u00af\b\n\1\2\u00a6\u00a7\7\20\2\2"+
		"\u00a7\u00af\b\n\1\2\u00a8\u00a9\7\21\2\2\u00a9\u00af\b\n\1\2\u00aa\u00ab"+
		"\7\22\2\2\u00ab\u00af\b\n\1\2\u00ac\u00ad\7.\2\2\u00ad\u00af\b\n\1\2\u00ae"+
		"\u00a4\3\2\2\2\u00ae\u00a6\3\2\2\2\u00ae\u00a8\3\2\2\2\u00ae\u00aa\3\2"+
		"\2\2\u00ae\u00ac\3\2\2\2\u00af\23\3\2\2\2\u00b0\u00b6\7\5\2\2\u00b1\u00b2"+
		"\5\26\f\2\u00b2\u00b3\b\13\1\2\u00b3\u00b5\3\2\2\2\u00b4\u00b1\3\2\2\2"+
		"\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b9"+
		"\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9\u00ba\7\6\2\2\u00ba\u00bb\b\13\1\2"+
		"\u00bb\25\3\2\2\2\u00bc\u00bd\5\24\13\2\u00bd\u00be\b\f\1\2\u00be\u010e"+
		"\3\2\2\2\u00bf\u00c0\7\23\2\2\u00c0\u00c1\7\t\2\2\u00c1\u00c2\5\32\16"+
		"\2\u00c2\u00c3\7\n\2\2\u00c3\u00c4\5\26\f\2\u00c4\u00c5\b\f\1\2\u00c5"+
		"\u010e\3\2\2\2\u00c6\u00c7\7\23\2\2\u00c7\u00c8\7\t\2\2\u00c8\u00c9\5"+
		"\32\16\2\u00c9\u00ca\7\n\2\2\u00ca\u00cb\5\26\f\2\u00cb\u00cc\7\24\2\2"+
		"\u00cc\u00cd\5\26\f\2\u00cd\u00ce\b\f\1\2\u00ce\u010e\3\2\2\2\u00cf\u00d0"+
		"\7\25\2\2\u00d0\u00d1\7\t\2\2\u00d1\u00d2\5\30\r\2\u00d2\u00d3\7\n\2\2"+
		"\u00d3\u00d4\5\26\f\2\u00d4\u00d5\b\f\1\2\u00d5\u010e\3\2\2\2\u00d6\u00d7"+
		"\7\26\2\2\u00d7\u00d8\5\34\17\2\u00d8\u00d9\7\b\2\2\u00d9\u00da\b\f\1"+
		"\2\u00da\u010e\3\2\2\2\u00db\u00dc\7\27\2\2\u00dc\u00dd\5\32\16\2\u00dd"+
		"\u00de\7\b\2\2\u00de\u00df\b\f\1\2\u00df\u010e\3\2\2\2\u00e0\u00e1\7\30"+
		"\2\2\u00e1\u00e2\5\32\16\2\u00e2\u00e9\b\f\1\2\u00e3\u00e4\7\f\2\2\u00e4"+
		"\u00e5\5\32\16\2\u00e5\u00e6\b\f\1\2\u00e6\u00e8\3\2\2\2\u00e7\u00e3\3"+
		"\2\2\2\u00e8\u00eb\3\2\2\2\u00e9\u00e7\3\2\2\2\u00e9\u00ea\3\2\2\2\u00ea"+
		"\u00ec\3\2\2\2\u00eb\u00e9\3\2\2\2\u00ec\u00ed\b\f\1\2\u00ed\u00ee\7\b"+
		"\2\2\u00ee\u010e\3\2\2\2\u00ef\u00f0\5\34\17\2\u00f0\u00f1\7\31\2\2\u00f1"+
		"\u00f2\5\32\16\2\u00f2\u00f3\7\b\2\2\u00f3\u00f4\b\f\1\2\u00f4\u010e\3"+
		"\2\2\2\u00f5\u00f6\7-\2\2\u00f6\u00f8\7\t\2\2\u00f7\u00f9\5\36\20\2\u00f8"+
		"\u00f7\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\u0109\7\n"+
		"\2\2\u00fb\u00fc\7\32\2\2\u00fc\u00fd\5\34\17\2\u00fd\u0104\b\f\1\2\u00fe"+
		"\u00ff\7\f\2\2\u00ff\u0100\5\34\17\2\u0100\u0101\b\f\1\2\u0101\u0103\3"+
		"\2\2\2\u0102\u00fe\3\2\2\2\u0103\u0106\3\2\2\2\u0104\u0102\3\2\2\2\u0104"+
		"\u0105\3\2\2\2\u0105\u0107\3\2\2\2\u0106\u0104\3\2\2\2\u0107\u0108\7\33"+
		"\2\2\u0108\u010a\3\2\2\2\u0109\u00fb\3\2\2\2\u0109\u010a\3\2\2\2\u010a"+
		"\u010b\3\2\2\2\u010b\u010c\b\f\1\2\u010c\u010e\7\b\2\2\u010d\u00bc\3\2"+
		"\2\2\u010d\u00bf\3\2\2\2\u010d\u00c6\3\2\2\2\u010d\u00cf\3\2\2\2\u010d"+
		"\u00d6\3\2\2\2\u010d\u00db\3\2\2\2\u010d\u00e0\3\2\2\2\u010d\u00ef\3\2"+
		"\2\2\u010d\u00f5\3\2\2\2\u010e\27\3\2\2\2\u010f\u0110\7-\2\2\u0110\u0111"+
		"\7\13\2\2\u0111\u0112\5\32\16\2\u0112\u0113\b\r\1\2\u0113\u0118\3\2\2"+
		"\2\u0114\u0115\5\32\16\2\u0115\u0116\b\r\1\2\u0116\u0118\3\2\2\2\u0117"+
		"\u010f\3\2\2\2\u0117\u0114\3\2\2\2\u0118\31\3\2\2\2\u0119\u011a\b\16\1"+
		"\2\u011a\u011b\7$\2\2\u011b\u011c\5\32\16\17\u011c\u011d\b\16\1\2\u011d"+
		"\u014e\3\2\2\2\u011e\u011f\7 \2\2\u011f\u0120\5\32\16\16\u0120\u0121\b"+
		"\16\1\2\u0121\u014e\3\2\2\2\u0122\u0123\5\34\17\2\u0123\u0124\b\16\1\2"+
		"\u0124\u014e\3\2\2\2\u0125\u0126\7\t\2\2\u0126\u0127\5\32\16\2\u0127\u0128"+
		"\7\n\2\2\u0128\u0129\b\16\1\2\u0129\u014e\3\2\2\2\u012a\u012b\7%\2\2\u012b"+
		"\u012c\5\20\t\2\u012c\u012d\7\r\2\2\u012d\u012e\5\32\16\2\u012e\u012f"+
		"\7\16\2\2\u012f\u0130\3\2\2\2\u0130\u0131\b\16\1\2\u0131\u014e\3\2\2\2"+
		"\u0132\u0133\7%\2\2\u0133\u0134\5\22\n\2\u0134\u0135\b\16\1\2\u0135\u014e"+
		"\3\2\2\2\u0136\u0137\7-\2\2\u0137\u0139\7\t\2\2\u0138\u013a\5\36\20\2"+
		"\u0139\u0138\3\2\2\2\u0139\u013a\3\2\2\2\u013a\u013b\3\2\2\2\u013b\u013c"+
		"\7\n\2\2\u013c\u013d\7\r\2\2\u013d\u013e\5\32\16\2\u013e\u013f\7\16\2"+
		"\2\u013f\u0140\b\16\1\2\u0140\u014e\3\2\2\2\u0141\u0142\7&\2\2\u0142\u014e"+
		"\b\16\1\2\u0143\u0144\7\'\2\2\u0144\u014e\b\16\1\2\u0145\u0146\7(\2\2"+
		"\u0146\u014e\b\16\1\2\u0147\u0148\7*\2\2\u0148\u014e\b\16\1\2\u0149\u014a"+
		"\7+\2\2\u014a\u014e\b\16\1\2\u014b\u014c\7,\2\2\u014c\u014e\b\16\1\2\u014d"+
		"\u0119\3\2\2\2\u014d\u011e\3\2\2\2\u014d\u0122\3\2\2\2\u014d\u0125\3\2"+
		"\2\2\u014d\u012a\3\2\2\2\u014d\u0132\3\2\2\2\u014d\u0136\3\2\2\2\u014d"+
		"\u0141\3\2\2\2\u014d\u0143\3\2\2\2\u014d\u0145\3\2\2\2\u014d\u0147\3\2"+
		"\2\2\u014d\u0149\3\2\2\2\u014d\u014b\3\2\2\2\u014e\u017e\3\2\2\2\u014f"+
		"\u0150\f\30\2\2\u0150\u0151\7\34\2\2\u0151\u0152\5\32\16\31\u0152\u0153"+
		"\b\16\1\2\u0153\u017d\3\2\2\2\u0154\u0155\f\27\2\2\u0155\u0156\7\35\2"+
		"\2\u0156\u0157\5\32\16\30\u0157\u0158\b\16\1\2\u0158\u017d\3\2\2\2\u0159"+
		"\u015a\f\26\2\2\u015a\u015b\7\36\2\2\u015b\u015c\5\32\16\27\u015c\u015d"+
		"\b\16\1\2\u015d\u017d\3\2\2\2\u015e\u015f\f\25\2\2\u015f\u0160\7\37\2"+
		"\2\u0160\u0161\5\32\16\26\u0161\u0162\b\16\1\2\u0162\u017d\3\2\2\2\u0163"+
		"\u0164\f\24\2\2\u0164\u0165\7 \2\2\u0165\u0166\5\32\16\25\u0166\u0167"+
		"\b\16\1\2\u0167\u017d\3\2\2\2\u0168\u0169\f\23\2\2\u0169\u016a\7\32\2"+
		"\2\u016a\u016b\5\32\16\24\u016b\u016c\b\16\1\2\u016c\u017d\3\2\2\2\u016d"+
		"\u016e\f\22\2\2\u016e\u016f\7!\2\2\u016f\u0170\5\32\16\23\u0170\u0171"+
		"\b\16\1\2\u0171\u017d\3\2\2\2\u0172\u0173\f\21\2\2\u0173\u0174\7\"\2\2"+
		"\u0174\u0175\5\32\16\22\u0175\u0176\b\16\1\2\u0176\u017d\3\2\2\2\u0177"+
		"\u0178\f\20\2\2\u0178\u0179\7#\2\2\u0179\u017a\5\32\16\21\u017a\u017b"+
		"\b\16\1\2\u017b\u017d\3\2\2\2\u017c\u014f\3\2\2\2\u017c\u0154\3\2\2\2"+
		"\u017c\u0159\3\2\2\2\u017c\u015e\3\2\2\2\u017c\u0163\3\2\2\2\u017c\u0168"+
		"\3\2\2\2\u017c\u016d\3\2\2\2\u017c\u0172\3\2\2\2\u017c\u0177\3\2\2\2\u017d"+
		"\u0180\3\2\2\2\u017e\u017c\3\2\2\2\u017e\u017f\3\2\2\2\u017f\33\3\2\2"+
		"\2\u0180\u017e\3\2\2\2\u0181\u0182\b\17\1\2\u0182\u0183\7-\2\2\u0183\u0184"+
		"\b\17\1\2\u0184\u0191\3\2\2\2\u0185\u0186\f\4\2\2\u0186\u0187\7\r\2\2"+
		"\u0187\u0188\5\32\16\2\u0188\u0189\7\16\2\2\u0189\u018a\b\17\1\2\u018a"+
		"\u0190\3\2\2\2\u018b\u018c\f\3\2\2\u018c\u018d\7)\2\2\u018d\u018e\7-\2"+
		"\2\u018e\u0190\b\17\1\2\u018f\u0185\3\2\2\2\u018f\u018b\3\2\2\2\u0190"+
		"\u0193\3\2\2\2\u0191\u018f\3\2\2\2\u0191\u0192\3\2\2\2\u0192\35\3\2\2"+
		"\2\u0193\u0191\3\2\2\2\u0194\u0195\5\32\16\2\u0195\u019c\b\20\1\2\u0196"+
		"\u0197\7\f\2\2\u0197\u0198\5\32\16\2\u0198\u0199\b\20\1\2\u0199\u019b"+
		"\3\2\2\2\u019a\u0196\3\2\2\2\u019b\u019e\3\2\2\2\u019c\u019a\3\2\2\2\u019c"+
		"\u019d\3\2\2\2\u019d\u019f\3\2\2\2\u019e\u019c\3\2\2\2\u019f\u01a0\b\20"+
		"\1\2\u01a0\37\3\2\2\2\37%\60<>KP[gjr~\u0081\u0092\u00a1\u00ae\u00b6\u00e9"+
		"\u00f8\u0104\u0109\u010d\u0117\u0139\u014d\u017c\u017e\u018f\u0191\u019c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}