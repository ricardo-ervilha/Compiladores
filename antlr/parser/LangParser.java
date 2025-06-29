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
		RULE_prog = 0, RULE_def = 1, RULE_data = 2, RULE_decl = 3, RULE_fun = 4, 
		RULE_params = 5, RULE_type = 6, RULE_block = 7, RULE_cmd = 8, RULE_itcond = 9, 
		RULE_exp = 10, RULE_lvalue = 11, RULE_exps = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "def", "data", "decl", "fun", "params", "type", "block", "cmd", 
			"itcond", "exp", "lvalue", "exps"
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);

		            List<Def> defList = new ArrayList<>();
		        
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << ID))) != 0)) {
				{
				{
				setState(26);
				((ProgContext)_localctx).s1 = def();
				 defList.add(((ProgContext)_localctx).s1.ast); 
				}
				}
				setState(33);
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitDef(this);
		}
	}

	public final DefContext def() throws RecognitionException {
		DefContext _localctx = new DefContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_def);
		try {
			setState(42);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(36);
				((DefContext)_localctx).d1 = data();
				 ((DefContext)_localctx).ast =  ((DefContext)_localctx).d1.ast; 
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(39);
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
		public FunContext fun;
		public Token d;
		public TerminalNode TYID() { return getToken(LangParser.TYID, 0); }
		public List<DeclContext> decl() {
			return getRuleContexts(DeclContext.class);
		}
		public DeclContext decl(int i) {
			return getRuleContext(DeclContext.class,i);
		}
		public List<FunContext> fun() {
			return getRuleContexts(FunContext.class);
		}
		public FunContext fun(int i) {
			return getRuleContext(FunContext.class,i);
		}
		public DataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_data; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterData(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitData(this);
		}
	}

	public final DataContext data() throws RecognitionException {
		DataContext _localctx = new DataContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_data);

				List<Node> members = new ArrayList<>();
			
		int _la;
		try {
			setState(74);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(44);
				((DataContext)_localctx).abs = match(T__0);
				setState(45);
				match(T__1);
				setState(46);
				((DataContext)_localctx).TYID = match(TYID);
				setState(47);
				match(T__2);
				setState(56);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ID) {
					{
					setState(54);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
					case 1:
						{
						setState(48);
						((DataContext)_localctx).decl = decl();
						 members.add(((DataContext)_localctx).decl.ast); 
						}
						break;
					case 2:
						{
						setState(51);
						((DataContext)_localctx).fun = fun();
						 members.add(((DataContext)_localctx).fun.ast); 
						}
						break;
					}
					}
					setState(58);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(59);
				match(T__3);

				        	((DataContext)_localctx).ast =  new AbstractDataDecl((((DataContext)_localctx).abs!=null?((DataContext)_localctx).abs.getLine():0), (((DataContext)_localctx).abs!=null?((DataContext)_localctx).abs.getCharPositionInLine():0), (((DataContext)_localctx).TYID!=null?((DataContext)_localctx).TYID.getText():null), members);
				    
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(61);
				((DataContext)_localctx).d = match(T__1);
				setState(62);
				((DataContext)_localctx).TYID = match(TYID);
				setState(63);
				match(T__2);
				setState(69);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ID) {
					{
					{
					setState(64);
					((DataContext)_localctx).decl = decl();
					 members.add(((DataContext)_localctx).decl.ast); 
					}
					}
					setState(71);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(72);
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_decl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			((DeclContext)_localctx).id = match(ID);
			setState(77);
			match(T__4);
			setState(78);
			((DeclContext)_localctx).t = type(0);
			setState(79);
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterFun(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitFun(this);
		}
	}

	public final FunContext fun() throws RecognitionException {
		FunContext _localctx = new FunContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_fun);

		    	List<Type> members = new ArrayList<>();
		  	
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			((FunContext)_localctx).fun_def = match(ID);
			setState(83);
			match(T__6);
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(84);
				((FunContext)_localctx).p = params();
				}
			}

			setState(87);
			match(T__7);
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(88);
				match(T__8);
				setState(89);
				((FunContext)_localctx).t1 = type(0);
				 members.add(((FunContext)_localctx).t1.ast); 
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(91);
					match(T__9);
					setState(92);
					((FunContext)_localctx).t = type(0);
					 members.add(((FunContext)_localctx).t.ast); 
					}
					}
					setState(99);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(102);
			((FunContext)_localctx).cmd = cmd();

					((FunContext)_localctx).ast =  new Fun((((FunContext)_localctx).fun_def!=null?((FunContext)_localctx).fun_def.getLine():0), (((FunContext)_localctx).fun_def!=null?((FunContext)_localctx).fun_def.getCharPositionInLine():0), (((FunContext)_localctx).fun_def!=null?((FunContext)_localctx).fun_def.getText():null), ((FunContext)_localctx).p.ast, members, ((FunContext)_localctx).cmd.ast);
				
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterParams(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitParams(this);
		}
	}

	public final ParamsContext params() throws RecognitionException {
		ParamsContext _localctx = new ParamsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_params);

			    List<Param> listParam = new ArrayList<>();
			
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			((ParamsContext)_localctx).id1 = match(ID);
			setState(106);
			match(T__4);
			setState(107);
			((ParamsContext)_localctx).t1 = type(0);
			listParam.add(new Param((((ParamsContext)_localctx).id1!=null?((ParamsContext)_localctx).id1.getText():null), ((ParamsContext)_localctx).t1.ast));
			setState(117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(109);
				match(T__9);
				setState(110);
				((ParamsContext)_localctx).id2 = match(ID);
				setState(111);
				match(T__4);
				setState(112);
				((ParamsContext)_localctx).t2 = type(0);
				listParam.add(new Param((((ParamsContext)_localctx).id1!=null?((ParamsContext)_localctx).id1.getText():null), ((ParamsContext)_localctx).t2.ast));
				}
				}
				setState(119);
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
		public Token Int1;
		public Token Char1;
		public Token Bool1;
		public Token Float1;
		public Token TYID;
		public TerminalNode TYID() { return getToken(LangParser.TYID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		return type(0);
	}

	private TypeContext type(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		TypeContext _localctx = new TypeContext(_ctx, _parentState);
		TypeContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_type, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__12:
				{
				setState(123);
				((TypeContext)_localctx).Int1 = match(T__12);
				((TypeContext)_localctx).ast =  new TypeInt((((TypeContext)_localctx).Int1!=null?((TypeContext)_localctx).Int1.getLine():0), (((TypeContext)_localctx).Int1!=null?((TypeContext)_localctx).Int1.getCharPositionInLine():0));
				}
				break;
			case T__13:
				{
				setState(125);
				((TypeContext)_localctx).Char1 = match(T__13);
				((TypeContext)_localctx).ast =  new TypeChar((((TypeContext)_localctx).Char1!=null?((TypeContext)_localctx).Char1.getLine():0), (((TypeContext)_localctx).Char1!=null?((TypeContext)_localctx).Char1.getCharPositionInLine():0));
				}
				break;
			case T__14:
				{
				setState(127);
				((TypeContext)_localctx).Bool1 = match(T__14);
				((TypeContext)_localctx).ast =  new TypeBool((((TypeContext)_localctx).Bool1!=null?((TypeContext)_localctx).Bool1.getLine():0), (((TypeContext)_localctx).Bool1!=null?((TypeContext)_localctx).Bool1.getCharPositionInLine():0));
				}
				break;
			case T__15:
				{
				setState(129);
				((TypeContext)_localctx).Float1 = match(T__15);
				((TypeContext)_localctx).ast =  new TypeFloat((((TypeContext)_localctx).Float1!=null?((TypeContext)_localctx).Float1.getLine():0), (((TypeContext)_localctx).Float1!=null?((TypeContext)_localctx).Float1.getCharPositionInLine():0));
				}
				break;
			case TYID:
				{
				setState(131);
				((TypeContext)_localctx).TYID = match(TYID);
				((TypeContext)_localctx).ast =  new TYID((((TypeContext)_localctx).TYID!=null?((TypeContext)_localctx).TYID.getLine():0), (((TypeContext)_localctx).TYID!=null?((TypeContext)_localctx).TYID.getCharPositionInLine():0), (((TypeContext)_localctx).TYID!=null?((TypeContext)_localctx).TYID.getText():null));
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(141);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
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
					setState(135);
					if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
					setState(136);
					match(T__10);
					setState(137);
					match(T__11);
					((TypeContext)_localctx).ast =  new ArrayType(((TypeContext)_localctx).t1.ast.getLine(), ((TypeContext)_localctx).t1.ast.getCol());
					}
					} 
				}
				setState(143);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_block);
		List<Cmd> listCmd = new ArrayList<>();
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			match(T__2);
			setState(150);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__16) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << ID))) != 0)) {
				{
				{
				setState(145);
				((BlockContext)_localctx).c1 = cmd();
				listCmd.add(((BlockContext)_localctx).c1.ast);
				}
				}
				setState(152);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			((BlockContext)_localctx).ast =  new Block(((BlockContext)_localctx).c1.ast.getLine(), ((BlockContext)_localctx).c1.ast.getCol(), listCmd); 
			setState(154);
			match(T__3);
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitCmd(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_cmd);

		    	List<LValue> membersLValue = new ArrayList<>();
				List<Expr> membersReturn = new ArrayList<>();
		  	
		int _la;
		try {
			setState(237);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(156);
				((CmdContext)_localctx).block = block();
				 ((CmdContext)_localctx).ast =  ((CmdContext)_localctx).block.ast;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(159);
				((CmdContext)_localctx).ifCond = match(T__16);
				setState(160);
				match(T__6);
				setState(161);
				((CmdContext)_localctx).exp = exp(0);
				setState(162);
				match(T__7);
				setState(163);
				((CmdContext)_localctx).cmd = cmd();
				 ((CmdContext)_localctx).ast =  new CmdIf((((CmdContext)_localctx).ifCond!=null?((CmdContext)_localctx).ifCond.getLine():0), (((CmdContext)_localctx).ifCond!=null?((CmdContext)_localctx).ifCond.getCharPositionInLine():0), ((CmdContext)_localctx).exp.ast, ((CmdContext)_localctx).cmd.ast);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(166);
				((CmdContext)_localctx).ifCond = match(T__16);
				setState(167);
				match(T__6);
				setState(168);
				((CmdContext)_localctx).exp = exp(0);
				setState(169);
				match(T__7);
				setState(170);
				((CmdContext)_localctx).cmd1 = cmd();
				setState(171);
				match(T__17);
				setState(172);
				((CmdContext)_localctx).cmd2 = cmd();
				 ((CmdContext)_localctx).ast =  new CmdIf((((CmdContext)_localctx).ifCond!=null?((CmdContext)_localctx).ifCond.getLine():0), (((CmdContext)_localctx).ifCond!=null?((CmdContext)_localctx).ifCond.getCharPositionInLine():0), ((CmdContext)_localctx).exp.ast, ((CmdContext)_localctx).cmd1.ast, ((CmdContext)_localctx).cmd2.ast );
						
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(175);
				((CmdContext)_localctx).it = match(T__18);
				setState(176);
				match(T__6);
				setState(177);
				((CmdContext)_localctx).itcond = itcond();
				setState(178);
				match(T__7);
				setState(179);
				((CmdContext)_localctx).cmd = cmd();
				 ((CmdContext)_localctx).ast =  new CmdIterate((((CmdContext)_localctx).it!=null?((CmdContext)_localctx).it.getLine():0), (((CmdContext)_localctx).it!=null?((CmdContext)_localctx).it.getCharPositionInLine():0), ((CmdContext)_localctx).itcond.ast, ((CmdContext)_localctx).cmd.ast );
						
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(182);
				((CmdContext)_localctx).rd = match(T__19);
				setState(183);
				((CmdContext)_localctx).lvalue = lvalue(0);
				setState(184);
				match(T__5);
				 ((CmdContext)_localctx).ast =  new CmdRead((((CmdContext)_localctx).rd!=null?((CmdContext)_localctx).rd.getLine():0), (((CmdContext)_localctx).rd!=null?((CmdContext)_localctx).rd.getCharPositionInLine():0), ((CmdContext)_localctx).lvalue.ast); 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(187);
				((CmdContext)_localctx).prt = match(T__20);
				setState(188);
				((CmdContext)_localctx).exp = exp(0);
				setState(189);
				match(T__5);
				 ((CmdContext)_localctx).ast =  new CmdPrint((((CmdContext)_localctx).prt!=null?((CmdContext)_localctx).prt.getLine():0), (((CmdContext)_localctx).prt!=null?((CmdContext)_localctx).prt.getCharPositionInLine():0), ((CmdContext)_localctx).exp.ast); 
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(192);
				((CmdContext)_localctx).rt = match(T__21);
				setState(193);
				((CmdContext)_localctx).exp = exp(0);
				 membersReturn.add(((CmdContext)_localctx).exp.ast) ;
				setState(201);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(195);
					match(T__9);
					setState(196);
					((CmdContext)_localctx).exp = exp(0);
					membersReturn.add(((CmdContext)_localctx).exp.ast);
					}
					}
					setState(203);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				 ((CmdContext)_localctx).ast =  new CmdReturn((((CmdContext)_localctx).rt!=null?((CmdContext)_localctx).rt.getLine():0), (((CmdContext)_localctx).rt!=null?((CmdContext)_localctx).rt.getCharPositionInLine():0), membersReturn);
				setState(205);
				match(T__5);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(207);
				((CmdContext)_localctx).lvalue = lvalue(0);
				setState(208);
				match(T__22);
				setState(209);
				((CmdContext)_localctx).exp = exp(0);
				setState(210);
				match(T__5);
				 ((CmdContext)_localctx).ast =  new CmdAssign(((CmdContext)_localctx).lvalue.ast.getLine(), ((CmdContext)_localctx).lvalue.ast.getCol(), ((CmdContext)_localctx).lvalue.ast, ((CmdContext)_localctx).exp.ast);
						
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(213);
				((CmdContext)_localctx).ID = match(ID);
				setState(214);
				match(T__6);
				setState(216);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__29) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << INT) | (1L << FLOAT) | (1L << CHAR) | (1L << ID))) != 0)) {
					{
					setState(215);
					((CmdContext)_localctx).exps = exps();
					}
				}

				setState(218);
				match(T__7);
				setState(234);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__23) {
					{
					setState(219);
					match(T__23);
					setState(220);
					((CmdContext)_localctx).l1 = lvalue(0);
					 membersLValue.add(((CmdContext)_localctx).l1.ast) ;
					setState(228);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__9) {
						{
						{
						setState(222);
						match(T__9);
						setState(223);
						((CmdContext)_localctx).l2 = lvalue(0);
						 membersLValue.add(((CmdContext)_localctx).l2.ast) ;
						}
						}
						setState(230);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(231);
					match(T__24);
					((CmdContext)_localctx).ast =  new CmdFuncCall((((CmdContext)_localctx).ID!=null?((CmdContext)_localctx).ID.getLine():0), (((CmdContext)_localctx).ID!=null?((CmdContext)_localctx).ID.getCharPositionInLine():0),(((CmdContext)_localctx).ID!=null?((CmdContext)_localctx).ID.getText():null), ((CmdContext)_localctx).exps.ast, membersLValue );
								
					}
				}

				setState(236);
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterItcond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitItcond(this);
		}
	}

	public final ItcondContext itcond() throws RecognitionException {
		ItcondContext _localctx = new ItcondContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_itcond);
		try {
			setState(247);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(239);
				((ItcondContext)_localctx).ID = match(ID);
				setState(240);
				match(T__8);
				setState(241);
				((ItcondContext)_localctx).exp = exp(0);
				 ((ItcondContext)_localctx).ast =  new IdItCond((((ItcondContext)_localctx).ID!=null?((ItcondContext)_localctx).ID.getLine():0), (((ItcondContext)_localctx).ID!=null?((ItcondContext)_localctx).ID.getCharPositionInLine():0), (((ItcondContext)_localctx).ID!=null?((ItcondContext)_localctx).ID.getText():null), ((ItcondContext)_localctx).exp.ast);
						
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(244);
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitExp(this);
		}
	}

	public final ExpContext exp() throws RecognitionException {
		return exp(0);
	}

	private ExpContext exp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpContext _localctx = new ExpContext(_ctx, _parentState);
		ExpContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_exp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(250);
				((ExpContext)_localctx).not = match(T__33);
				setState(251);
				((ExpContext)_localctx).exp = exp(12);
				 ((ExpContext)_localctx).ast =  new NotExpr((((ExpContext)_localctx).not!=null?((ExpContext)_localctx).not.getLine():0), (((ExpContext)_localctx).not!=null?((ExpContext)_localctx).not.getCharPositionInLine():0), ((ExpContext)_localctx).exp.ast);
				}
				break;
			case 2:
				{
				setState(254);
				((ExpContext)_localctx).minus = match(T__29);
				setState(255);
				((ExpContext)_localctx).exp = exp(11);
				 ((ExpContext)_localctx).ast =  new MinusExpr((((ExpContext)_localctx).minus!=null?((ExpContext)_localctx).minus.getLine():0), (((ExpContext)_localctx).minus!=null?((ExpContext)_localctx).minus.getCharPositionInLine():0), ((ExpContext)_localctx).exp.ast);
				}
				break;
			case 3:
				{
				setState(258);
				((ExpContext)_localctx).lvalue = lvalue(0);
				 ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).lvalue.ast;
						
				}
				break;
			case 4:
				{
				setState(261);
				match(T__6);
				setState(262);
				((ExpContext)_localctx).exp = exp(0);
				setState(263);
				match(T__7);
				 ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).exp.ast; 
				}
				break;
			case 5:
				{
				setState(266);
				match(T__34);
				setState(267);
				((ExpContext)_localctx).type = type(0);
				setState(272);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
				case 1:
					{
					setState(268);
					match(T__10);
					setState(269);
					((ExpContext)_localctx).exp = exp(0);
					setState(270);
					match(T__11);
					}
					break;
				}
				 ((ExpContext)_localctx).ast =  new VarExpr(((ExpContext)_localctx).exp.ast.getLine(), ((ExpContext)_localctx).exp.ast.getCol(), ((ExpContext)_localctx).type.ast, ((ExpContext)_localctx).exp.ast);
						
				}
				break;
			case 6:
				{
				setState(276);
				((ExpContext)_localctx).ID = match(ID);
				setState(277);
				match(T__6);
				setState(279);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__29) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << INT) | (1L << FLOAT) | (1L << CHAR) | (1L << ID))) != 0)) {
					{
					setState(278);
					((ExpContext)_localctx).exps = exps();
					}
				}

				setState(281);
				match(T__7);
				setState(282);
				match(T__10);
				setState(283);
				((ExpContext)_localctx).exp = exp(0);
				setState(284);
				match(T__11);
				 ((ExpContext)_localctx).ast =  new CallFunctionAccess((((ExpContext)_localctx).ID!=null?((ExpContext)_localctx).ID.getLine():0), (((ExpContext)_localctx).ID!=null?((ExpContext)_localctx).ID.getCharPositionInLine():0), (((ExpContext)_localctx).ID!=null?((ExpContext)_localctx).ID.getText():null), ((ExpContext)_localctx).exps.ast, ((ExpContext)_localctx).exp.ast);
						
				}
				break;
			case 7:
				{
				setState(287);
				((ExpContext)_localctx).t = match(T__35);
				 ((ExpContext)_localctx).ast =  new TrueValue((((ExpContext)_localctx).t!=null?((ExpContext)_localctx).t.getLine():0), (((ExpContext)_localctx).t!=null?((ExpContext)_localctx).t.getCharPositionInLine():0));
				}
				break;
			case 8:
				{
				setState(289);
				((ExpContext)_localctx).f = match(T__36);
				 ((ExpContext)_localctx).ast =  new FalseValue((((ExpContext)_localctx).f!=null?((ExpContext)_localctx).f.getLine():0), (((ExpContext)_localctx).f!=null?((ExpContext)_localctx).f.getCharPositionInLine():0));
				}
				break;
			case 9:
				{
				setState(291);
				((ExpContext)_localctx).n = match(T__37);
				 ((ExpContext)_localctx).ast =  new NullValue((((ExpContext)_localctx).n!=null?((ExpContext)_localctx).n.getLine():0), (((ExpContext)_localctx).n!=null?((ExpContext)_localctx).n.getCharPositionInLine():0));
				}
				break;
			case 10:
				{
				setState(293);
				((ExpContext)_localctx).INT = match(INT);
				 ((ExpContext)_localctx).ast =  new IntValue((((ExpContext)_localctx).INT!=null?((ExpContext)_localctx).INT.getLine():0), (((ExpContext)_localctx).INT!=null?((ExpContext)_localctx).INT.getCharPositionInLine():0), (((ExpContext)_localctx).INT!=null?((ExpContext)_localctx).INT.getText():null));
				}
				break;
			case 11:
				{
				setState(295);
				((ExpContext)_localctx).FLOAT = match(FLOAT);
				 ((ExpContext)_localctx).ast =  new FloatValue((((ExpContext)_localctx).FLOAT!=null?((ExpContext)_localctx).FLOAT.getLine():0), (((ExpContext)_localctx).FLOAT!=null?((ExpContext)_localctx).FLOAT.getCharPositionInLine():0), (((ExpContext)_localctx).FLOAT!=null?((ExpContext)_localctx).FLOAT.getText():null));
				}
				break;
			case 12:
				{
				setState(297);
				((ExpContext)_localctx).CHAR = match(CHAR);
				 ((ExpContext)_localctx).ast =  new CharValue((((ExpContext)_localctx).CHAR!=null?((ExpContext)_localctx).CHAR.getLine():0), (((ExpContext)_localctx).CHAR!=null?((ExpContext)_localctx).CHAR.getCharPositionInLine():0), (((ExpContext)_localctx).CHAR!=null?((ExpContext)_localctx).CHAR.getText():null));
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(348);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(346);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
					case 1:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						_localctx.exp1 = _prevctx;
						_localctx.exp1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(301);
						if (!(precpred(_ctx, 21))) throw new FailedPredicateException(this, "precpred(_ctx, 21)");
						setState(302);
						((ExpContext)_localctx).op = match(T__25);
						setState(303);
						((ExpContext)_localctx).exp2 = ((ExpContext)_localctx).exp = exp(22);
						 ((ExpContext)_localctx).ast =  new Mul(((ExpContext)_localctx).exp1.ast.getLine(), ((ExpContext)_localctx).exp1.ast.getCol(), ((ExpContext)_localctx).exp1.ast, ((ExpContext)_localctx).exp2.ast);
						}
						break;
					case 2:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						_localctx.exp1 = _prevctx;
						_localctx.exp1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(306);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(307);
						((ExpContext)_localctx).op = match(T__26);
						setState(308);
						((ExpContext)_localctx).exp2 = ((ExpContext)_localctx).exp = exp(21);
						 ((ExpContext)_localctx).ast =  new Div(((ExpContext)_localctx).exp1.ast.getLine(), ((ExpContext)_localctx).exp1.ast.getCol(), ((ExpContext)_localctx).exp1.ast, ((ExpContext)_localctx).exp2.ast);
						}
						break;
					case 3:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						_localctx.exp1 = _prevctx;
						_localctx.exp1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(311);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(312);
						((ExpContext)_localctx).op = match(T__27);
						setState(313);
						((ExpContext)_localctx).exp2 = ((ExpContext)_localctx).exp = exp(20);
						 ((ExpContext)_localctx).ast =  new Mod(((ExpContext)_localctx).exp1.ast.getLine(), ((ExpContext)_localctx).exp1.ast.getCol(), ((ExpContext)_localctx).exp1.ast, ((ExpContext)_localctx).exp2.ast);
						}
						break;
					case 4:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						_localctx.exp1 = _prevctx;
						_localctx.exp1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(316);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(317);
						((ExpContext)_localctx).op = match(T__28);
						setState(318);
						((ExpContext)_localctx).exp2 = ((ExpContext)_localctx).exp = exp(19);
						 ((ExpContext)_localctx).ast =  new Add(((ExpContext)_localctx).exp1.ast.getLine(), ((ExpContext)_localctx).exp1.ast.getCol(), ((ExpContext)_localctx).exp1.ast, ((ExpContext)_localctx).exp2.ast);
						}
						break;
					case 5:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						_localctx.exp1 = _prevctx;
						_localctx.exp1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(321);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(322);
						((ExpContext)_localctx).op = match(T__29);
						setState(323);
						((ExpContext)_localctx).exp2 = ((ExpContext)_localctx).exp = exp(18);
						 ((ExpContext)_localctx).ast =  new Sub(((ExpContext)_localctx).exp1.ast.getLine(), ((ExpContext)_localctx).exp1.ast.getCol(), ((ExpContext)_localctx).exp1.ast, ((ExpContext)_localctx).exp2.ast);
						}
						break;
					case 6:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						_localctx.exp1 = _prevctx;
						_localctx.exp1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(326);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(327);
						((ExpContext)_localctx).op = match(T__23);
						setState(328);
						((ExpContext)_localctx).exp2 = ((ExpContext)_localctx).exp = exp(17);
						 ((ExpContext)_localctx).ast =  new Lt(((ExpContext)_localctx).exp1.ast.getLine(), ((ExpContext)_localctx).exp1.ast.getCol(), ((ExpContext)_localctx).exp1.ast, ((ExpContext)_localctx).exp2.ast);
						}
						break;
					case 7:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						_localctx.exp1 = _prevctx;
						_localctx.exp1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(331);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(332);
						((ExpContext)_localctx).op = match(T__30);
						setState(333);
						((ExpContext)_localctx).exp2 = ((ExpContext)_localctx).exp = exp(16);
						 ((ExpContext)_localctx).ast =  new Eq(((ExpContext)_localctx).exp1.ast.getLine(), ((ExpContext)_localctx).exp1.ast.getCol(), ((ExpContext)_localctx).exp1.ast, ((ExpContext)_localctx).exp2.ast);
						}
						break;
					case 8:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						_localctx.exp1 = _prevctx;
						_localctx.exp1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(336);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(337);
						((ExpContext)_localctx).op = match(T__31);
						setState(338);
						((ExpContext)_localctx).exp2 = ((ExpContext)_localctx).exp = exp(15);
						 ((ExpContext)_localctx).ast =  new Diff(((ExpContext)_localctx).exp1.ast.getLine(), ((ExpContext)_localctx).exp1.ast.getCol(), ((ExpContext)_localctx).exp1.ast, ((ExpContext)_localctx).exp2.ast);
						}
						break;
					case 9:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						_localctx.exp1 = _prevctx;
						_localctx.exp1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(341);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(342);
						((ExpContext)_localctx).op = match(T__32);
						setState(343);
						((ExpContext)_localctx).exp2 = ((ExpContext)_localctx).exp = exp(14);
						 ((ExpContext)_localctx).ast =  new And(((ExpContext)_localctx).exp1.ast.getLine(), ((ExpContext)_localctx).exp1.ast.getCol(), ((ExpContext)_localctx).exp1.ast, ((ExpContext)_localctx).exp2.ast);
						}
						break;
					}
					} 
				}
				setState(350);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterLvalue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitLvalue(this);
		}
	}

	public final LvalueContext lvalue() throws RecognitionException {
		return lvalue(0);
	}

	private LvalueContext lvalue(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		LvalueContext _localctx = new LvalueContext(_ctx, _parentState);
		LvalueContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_lvalue, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(352);
			((LvalueContext)_localctx).ID = match(ID);
			 ((LvalueContext)_localctx).ast =  new ID((((LvalueContext)_localctx).ID!=null?((LvalueContext)_localctx).ID.getLine():0), (((LvalueContext)_localctx).ID!=null?((LvalueContext)_localctx).ID.getCharPositionInLine():0), (((LvalueContext)_localctx).ID!=null?((LvalueContext)_localctx).ID.getText():null));
			}
			_ctx.stop = _input.LT(-1);
			setState(367);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,25,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(365);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
					case 1:
						{
						_localctx = new LvalueContext(_parentctx, _parentState);
						_localctx.l1 = _prevctx;
						_localctx.l1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_lvalue);
						setState(355);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(356);
						match(T__10);
						setState(357);
						((LvalueContext)_localctx).exp = exp(0);
						setState(358);
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
						setState(361);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(362);
						match(T__38);
						setState(363);
						((LvalueContext)_localctx).ID = match(ID);
						((LvalueContext)_localctx).ast =  new IdLValue(((LvalueContext)_localctx).l1.ast.getLine(), ((LvalueContext)_localctx).l1.ast.getCol(), ((LvalueContext)_localctx).l1.ast, (((LvalueContext)_localctx).ID!=null?((LvalueContext)_localctx).ID.getText():null));
						}
						break;
					}
					} 
				}
				setState(369);
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterExps(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitExps(this);
		}
	}

	public final ExpsContext exps() throws RecognitionException {
		ExpsContext _localctx = new ExpsContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_exps);

		    	List<Expr> expsValues = new ArrayList<>();
		  	
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(370);
			((ExpsContext)_localctx).e1 = exp(0);
			expsValues.add(((ExpsContext)_localctx).e1.ast);
			setState(378);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(372);
				match(T__9);
				setState(373);
				((ExpsContext)_localctx).e2 = exp(0);
				expsValues.add(((ExpsContext)_localctx).e2.ast);
				}
				}
				setState(380);
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
		case 6:
			return type_sempred((TypeContext)_localctx, predIndex);
		case 10:
			return exp_sempred((ExpContext)_localctx, predIndex);
		case 11:
			return lvalue_sempred((LvalueContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean type_sempred(TypeContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 6);
		}
		return true;
	}
	private boolean exp_sempred(ExpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 21);
		case 2:
			return precpred(_ctx, 20);
		case 3:
			return precpred(_ctx, 19);
		case 4:
			return precpred(_ctx, 18);
		case 5:
			return precpred(_ctx, 17);
		case 6:
			return precpred(_ctx, 16);
		case 7:
			return precpred(_ctx, 15);
		case 8:
			return precpred(_ctx, 14);
		case 9:
			return precpred(_ctx, 13);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\61\u0182\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\3\2\3\2\7\2 \n\2\f\2\16\2#\13\2\3"+
		"\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\5\3-\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\7\49\n\4\f\4\16\4<\13\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4"+
		"F\n\4\f\4\16\4I\13\4\3\4\3\4\5\4M\n\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6"+
		"\3\6\5\6X\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6b\n\6\f\6\16\6e\13\6"+
		"\5\6g\n\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7v\n\7"+
		"\f\7\16\7y\13\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5"+
		"\b\u0088\n\b\3\b\3\b\3\b\3\b\7\b\u008e\n\b\f\b\16\b\u0091\13\b\3\t\3\t"+
		"\3\t\3\t\7\t\u0097\n\t\f\t\16\t\u009a\13\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\7\n\u00ca\n\n\f\n\16\n\u00cd\13\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u00db\n\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\7\n\u00e5\n\n\f\n\16\n\u00e8\13\n\3\n\3\n\3\n\5\n\u00ed\n\n\3\n\5"+
		"\n\u00f0\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00fa\n\13\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\5\f\u0113\n\f\3\f\3\f\3\f\3\f\3\f\5\f\u011a\n\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\5\f\u012e\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\7\f\u015d\n\f\f"+
		"\f\16\f\u0160\13\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\7\r\u0170\n\r\f\r\16\r\u0173\13\r\3\16\3\16\3\16\3\16\3\16\3\16\7"+
		"\16\u017b\n\16\f\16\16\16\u017e\13\16\3\16\3\16\3\16\2\5\16\26\30\17\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\2\2\2\u01aa\2!\3\2\2\2\4,\3\2\2\2\6L\3"+
		"\2\2\2\bN\3\2\2\2\nT\3\2\2\2\fk\3\2\2\2\16\u0087\3\2\2\2\20\u0092\3\2"+
		"\2\2\22\u00ef\3\2\2\2\24\u00f9\3\2\2\2\26\u012d\3\2\2\2\30\u0161\3\2\2"+
		"\2\32\u0174\3\2\2\2\34\35\5\4\3\2\35\36\b\2\1\2\36 \3\2\2\2\37\34\3\2"+
		"\2\2 #\3\2\2\2!\37\3\2\2\2!\"\3\2\2\2\"$\3\2\2\2#!\3\2\2\2$%\b\2\1\2%"+
		"\3\3\2\2\2&\'\5\6\4\2\'(\b\3\1\2(-\3\2\2\2)*\5\n\6\2*+\b\3\1\2+-\3\2\2"+
		"\2,&\3\2\2\2,)\3\2\2\2-\5\3\2\2\2./\7\3\2\2/\60\7\4\2\2\60\61\7.\2\2\61"+
		":\7\5\2\2\62\63\5\b\5\2\63\64\b\4\1\2\649\3\2\2\2\65\66\5\n\6\2\66\67"+
		"\b\4\1\2\679\3\2\2\28\62\3\2\2\28\65\3\2\2\29<\3\2\2\2:8\3\2\2\2:;\3\2"+
		"\2\2;=\3\2\2\2<:\3\2\2\2=>\7\6\2\2>M\b\4\1\2?@\7\4\2\2@A\7.\2\2AG\7\5"+
		"\2\2BC\5\b\5\2CD\b\4\1\2DF\3\2\2\2EB\3\2\2\2FI\3\2\2\2GE\3\2\2\2GH\3\2"+
		"\2\2HJ\3\2\2\2IG\3\2\2\2JK\7\6\2\2KM\b\4\1\2L.\3\2\2\2L?\3\2\2\2M\7\3"+
		"\2\2\2NO\7-\2\2OP\7\7\2\2PQ\5\16\b\2QR\7\b\2\2RS\b\5\1\2S\t\3\2\2\2TU"+
		"\7-\2\2UW\7\t\2\2VX\5\f\7\2WV\3\2\2\2WX\3\2\2\2XY\3\2\2\2Yf\7\n\2\2Z["+
		"\7\13\2\2[\\\5\16\b\2\\c\b\6\1\2]^\7\f\2\2^_\5\16\b\2_`\b\6\1\2`b\3\2"+
		"\2\2a]\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2dg\3\2\2\2ec\3\2\2\2fZ\3\2"+
		"\2\2fg\3\2\2\2gh\3\2\2\2hi\5\22\n\2ij\b\6\1\2j\13\3\2\2\2kl\7-\2\2lm\7"+
		"\7\2\2mn\5\16\b\2nw\b\7\1\2op\7\f\2\2pq\7-\2\2qr\7\7\2\2rs\5\16\b\2st"+
		"\b\7\1\2tv\3\2\2\2uo\3\2\2\2vy\3\2\2\2wu\3\2\2\2wx\3\2\2\2xz\3\2\2\2y"+
		"w\3\2\2\2z{\b\7\1\2{\r\3\2\2\2|}\b\b\1\2}~\7\17\2\2~\u0088\b\b\1\2\177"+
		"\u0080\7\20\2\2\u0080\u0088\b\b\1\2\u0081\u0082\7\21\2\2\u0082\u0088\b"+
		"\b\1\2\u0083\u0084\7\22\2\2\u0084\u0088\b\b\1\2\u0085\u0086\7.\2\2\u0086"+
		"\u0088\b\b\1\2\u0087|\3\2\2\2\u0087\177\3\2\2\2\u0087\u0081\3\2\2\2\u0087"+
		"\u0083\3\2\2\2\u0087\u0085\3\2\2\2\u0088\u008f\3\2\2\2\u0089\u008a\f\b"+
		"\2\2\u008a\u008b\7\r\2\2\u008b\u008c\7\16\2\2\u008c\u008e\b\b\1\2\u008d"+
		"\u0089\3\2\2\2\u008e\u0091\3\2\2\2\u008f\u008d\3\2\2\2\u008f\u0090\3\2"+
		"\2\2\u0090\17\3\2\2\2\u0091\u008f\3\2\2\2\u0092\u0098\7\5\2\2\u0093\u0094"+
		"\5\22\n\2\u0094\u0095\b\t\1\2\u0095\u0097\3\2\2\2\u0096\u0093\3\2\2\2"+
		"\u0097\u009a\3\2\2\2\u0098\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009b"+
		"\3\2\2\2\u009a\u0098\3\2\2\2\u009b\u009c\b\t\1\2\u009c\u009d\7\6\2\2\u009d"+
		"\21\3\2\2\2\u009e\u009f\5\20\t\2\u009f\u00a0\b\n\1\2\u00a0\u00f0\3\2\2"+
		"\2\u00a1\u00a2\7\23\2\2\u00a2\u00a3\7\t\2\2\u00a3\u00a4\5\26\f\2\u00a4"+
		"\u00a5\7\n\2\2\u00a5\u00a6\5\22\n\2\u00a6\u00a7\b\n\1\2\u00a7\u00f0\3"+
		"\2\2\2\u00a8\u00a9\7\23\2\2\u00a9\u00aa\7\t\2\2\u00aa\u00ab\5\26\f\2\u00ab"+
		"\u00ac\7\n\2\2\u00ac\u00ad\5\22\n\2\u00ad\u00ae\7\24\2\2\u00ae\u00af\5"+
		"\22\n\2\u00af\u00b0\b\n\1\2\u00b0\u00f0\3\2\2\2\u00b1\u00b2\7\25\2\2\u00b2"+
		"\u00b3\7\t\2\2\u00b3\u00b4\5\24\13\2\u00b4\u00b5\7\n\2\2\u00b5\u00b6\5"+
		"\22\n\2\u00b6\u00b7\b\n\1\2\u00b7\u00f0\3\2\2\2\u00b8\u00b9\7\26\2\2\u00b9"+
		"\u00ba\5\30\r\2\u00ba\u00bb\7\b\2\2\u00bb\u00bc\b\n\1\2\u00bc\u00f0\3"+
		"\2\2\2\u00bd\u00be\7\27\2\2\u00be\u00bf\5\26\f\2\u00bf\u00c0\7\b\2\2\u00c0"+
		"\u00c1\b\n\1\2\u00c1\u00f0\3\2\2\2\u00c2\u00c3\7\30\2\2\u00c3\u00c4\5"+
		"\26\f\2\u00c4\u00cb\b\n\1\2\u00c5\u00c6\7\f\2\2\u00c6\u00c7\5\26\f\2\u00c7"+
		"\u00c8\b\n\1\2\u00c8\u00ca\3\2\2\2\u00c9\u00c5\3\2\2\2\u00ca\u00cd\3\2"+
		"\2\2\u00cb\u00c9\3\2\2\2\u00cb\u00cc\3\2\2\2\u00cc\u00ce\3\2\2\2\u00cd"+
		"\u00cb\3\2\2\2\u00ce\u00cf\b\n\1\2\u00cf\u00d0\7\b\2\2\u00d0\u00f0\3\2"+
		"\2\2\u00d1\u00d2\5\30\r\2\u00d2\u00d3\7\31\2\2\u00d3\u00d4\5\26\f\2\u00d4"+
		"\u00d5\7\b\2\2\u00d5\u00d6\b\n\1\2\u00d6\u00f0\3\2\2\2\u00d7\u00d8\7-"+
		"\2\2\u00d8\u00da\7\t\2\2\u00d9\u00db\5\32\16\2\u00da\u00d9\3\2\2\2\u00da"+
		"\u00db\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00ec\7\n\2\2\u00dd\u00de\7\32"+
		"\2\2\u00de\u00df\5\30\r\2\u00df\u00e6\b\n\1\2\u00e0\u00e1\7\f\2\2\u00e1"+
		"\u00e2\5\30\r\2\u00e2\u00e3\b\n\1\2\u00e3\u00e5\3\2\2\2\u00e4\u00e0\3"+
		"\2\2\2\u00e5\u00e8\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7"+
		"\u00e9\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e9\u00ea\7\33\2\2\u00ea\u00eb\b"+
		"\n\1\2\u00eb\u00ed\3\2\2\2\u00ec\u00dd\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed"+
		"\u00ee\3\2\2\2\u00ee\u00f0\7\b\2\2\u00ef\u009e\3\2\2\2\u00ef\u00a1\3\2"+
		"\2\2\u00ef\u00a8\3\2\2\2\u00ef\u00b1\3\2\2\2\u00ef\u00b8\3\2\2\2\u00ef"+
		"\u00bd\3\2\2\2\u00ef\u00c2\3\2\2\2\u00ef\u00d1\3\2\2\2\u00ef\u00d7\3\2"+
		"\2\2\u00f0\23\3\2\2\2\u00f1\u00f2\7-\2\2\u00f2\u00f3\7\13\2\2\u00f3\u00f4"+
		"\5\26\f\2\u00f4\u00f5\b\13\1\2\u00f5\u00fa\3\2\2\2\u00f6\u00f7\5\26\f"+
		"\2\u00f7\u00f8\b\13\1\2\u00f8\u00fa\3\2\2\2\u00f9\u00f1\3\2\2\2\u00f9"+
		"\u00f6\3\2\2\2\u00fa\25\3\2\2\2\u00fb\u00fc\b\f\1\2\u00fc\u00fd\7$\2\2"+
		"\u00fd\u00fe\5\26\f\16\u00fe\u00ff\b\f\1\2\u00ff\u012e\3\2\2\2\u0100\u0101"+
		"\7 \2\2\u0101\u0102\5\26\f\r\u0102\u0103\b\f\1\2\u0103\u012e\3\2\2\2\u0104"+
		"\u0105\5\30\r\2\u0105\u0106\b\f\1\2\u0106\u012e\3\2\2\2\u0107\u0108\7"+
		"\t\2\2\u0108\u0109\5\26\f\2\u0109\u010a\7\n\2\2\u010a\u010b\b\f\1\2\u010b"+
		"\u012e\3\2\2\2\u010c\u010d\7%\2\2\u010d\u0112\5\16\b\2\u010e\u010f\7\r"+
		"\2\2\u010f\u0110\5\26\f\2\u0110\u0111\7\16\2\2\u0111\u0113\3\2\2\2\u0112"+
		"\u010e\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0115\b\f"+
		"\1\2\u0115\u012e\3\2\2\2\u0116\u0117\7-\2\2\u0117\u0119\7\t\2\2\u0118"+
		"\u011a\5\32\16\2\u0119\u0118\3\2\2\2\u0119\u011a\3\2\2\2\u011a\u011b\3"+
		"\2\2\2\u011b\u011c\7\n\2\2\u011c\u011d\7\r\2\2\u011d\u011e\5\26\f\2\u011e"+
		"\u011f\7\16\2\2\u011f\u0120\b\f\1\2\u0120\u012e\3\2\2\2\u0121\u0122\7"+
		"&\2\2\u0122\u012e\b\f\1\2\u0123\u0124\7\'\2\2\u0124\u012e\b\f\1\2\u0125"+
		"\u0126\7(\2\2\u0126\u012e\b\f\1\2\u0127\u0128\7*\2\2\u0128\u012e\b\f\1"+
		"\2\u0129\u012a\7+\2\2\u012a\u012e\b\f\1\2\u012b\u012c\7,\2\2\u012c\u012e"+
		"\b\f\1\2\u012d\u00fb\3\2\2\2\u012d\u0100\3\2\2\2\u012d\u0104\3\2\2\2\u012d"+
		"\u0107\3\2\2\2\u012d\u010c\3\2\2\2\u012d\u0116\3\2\2\2\u012d\u0121\3\2"+
		"\2\2\u012d\u0123\3\2\2\2\u012d\u0125\3\2\2\2\u012d\u0127\3\2\2\2\u012d"+
		"\u0129\3\2\2\2\u012d\u012b\3\2\2\2\u012e\u015e\3\2\2\2\u012f\u0130\f\27"+
		"\2\2\u0130\u0131\7\34\2\2\u0131\u0132\5\26\f\30\u0132\u0133\b\f\1\2\u0133"+
		"\u015d\3\2\2\2\u0134\u0135\f\26\2\2\u0135\u0136\7\35\2\2\u0136\u0137\5"+
		"\26\f\27\u0137\u0138\b\f\1\2\u0138\u015d\3\2\2\2\u0139\u013a\f\25\2\2"+
		"\u013a\u013b\7\36\2\2\u013b\u013c\5\26\f\26\u013c\u013d\b\f\1\2\u013d"+
		"\u015d\3\2\2\2\u013e\u013f\f\24\2\2\u013f\u0140\7\37\2\2\u0140\u0141\5"+
		"\26\f\25\u0141\u0142\b\f\1\2\u0142\u015d\3\2\2\2\u0143\u0144\f\23\2\2"+
		"\u0144\u0145\7 \2\2\u0145\u0146\5\26\f\24\u0146\u0147\b\f\1\2\u0147\u015d"+
		"\3\2\2\2\u0148\u0149\f\22\2\2\u0149\u014a\7\32\2\2\u014a\u014b\5\26\f"+
		"\23\u014b\u014c\b\f\1\2\u014c\u015d\3\2\2\2\u014d\u014e\f\21\2\2\u014e"+
		"\u014f\7!\2\2\u014f\u0150\5\26\f\22\u0150\u0151\b\f\1\2\u0151\u015d\3"+
		"\2\2\2\u0152\u0153\f\20\2\2\u0153\u0154\7\"\2\2\u0154\u0155\5\26\f\21"+
		"\u0155\u0156\b\f\1\2\u0156\u015d\3\2\2\2\u0157\u0158\f\17\2\2\u0158\u0159"+
		"\7#\2\2\u0159\u015a\5\26\f\20\u015a\u015b\b\f\1\2\u015b\u015d\3\2\2\2"+
		"\u015c\u012f\3\2\2\2\u015c\u0134\3\2\2\2\u015c\u0139\3\2\2\2\u015c\u013e"+
		"\3\2\2\2\u015c\u0143\3\2\2\2\u015c\u0148\3\2\2\2\u015c\u014d\3\2\2\2\u015c"+
		"\u0152\3\2\2\2\u015c\u0157\3\2\2\2\u015d\u0160\3\2\2\2\u015e\u015c\3\2"+
		"\2\2\u015e\u015f\3\2\2\2\u015f\27\3\2\2\2\u0160\u015e\3\2\2\2\u0161\u0162"+
		"\b\r\1\2\u0162\u0163\7-\2\2\u0163\u0164\b\r\1\2\u0164\u0171\3\2\2\2\u0165"+
		"\u0166\f\4\2\2\u0166\u0167\7\r\2\2\u0167\u0168\5\26\f\2\u0168\u0169\7"+
		"\16\2\2\u0169\u016a\b\r\1\2\u016a\u0170\3\2\2\2\u016b\u016c\f\3\2\2\u016c"+
		"\u016d\7)\2\2\u016d\u016e\7-\2\2\u016e\u0170\b\r\1\2\u016f\u0165\3\2\2"+
		"\2\u016f\u016b\3\2\2\2\u0170\u0173\3\2\2\2\u0171\u016f\3\2\2\2\u0171\u0172"+
		"\3\2\2\2\u0172\31\3\2\2\2\u0173\u0171\3\2\2\2\u0174\u0175\5\26\f\2\u0175"+
		"\u017c\b\16\1\2\u0176\u0177\7\f\2\2\u0177\u0178\5\26\f\2\u0178\u0179\b"+
		"\16\1\2\u0179\u017b\3\2\2\2\u017a\u0176\3\2\2\2\u017b\u017e\3\2\2\2\u017c"+
		"\u017a\3\2\2\2\u017c\u017d\3\2\2\2\u017d\u017f\3\2\2\2\u017e\u017c\3\2"+
		"\2\2\u017f\u0180\b\16\1\2\u0180\33\3\2\2\2\35!,8:GLWcfw\u0087\u008f\u0098"+
		"\u00cb\u00da\u00e6\u00ec\u00ef\u00f9\u0112\u0119\u012d\u015c\u015e\u016f"+
		"\u0171\u017c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}