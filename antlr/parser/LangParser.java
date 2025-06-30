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
		RULE_prog = 0, RULE_def = 1, RULE_data = 2, RULE_decl = 3, RULE_fun2 = 4, 
		RULE_fun = 5, RULE_params = 6, RULE_type = 7, RULE_block = 8, RULE_cmd = 9, 
		RULE_itcond = 10, RULE_exp = 11, RULE_lvalue = 12, RULE_exps = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "def", "data", "decl", "fun2", "fun", "params", "type", "block", 
			"cmd", "itcond", "exp", "lvalue", "exps"
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
			setState(33);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << ID))) != 0)) {
				{
				{
				setState(28);
				((ProgContext)_localctx).s1 = def();
				 defList.add(((ProgContext)_localctx).s1.ast); 
				}
				}
				setState(35);
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
			setState(44);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
			case T__1:
				enterOuterAlt(_localctx, 1);
				{
				setState(38);
				((DefContext)_localctx).d1 = data();
				 ((DefContext)_localctx).ast =  ((DefContext)_localctx).d1.ast; 
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(41);
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
	}

	public final DataContext data() throws RecognitionException {
		DataContext _localctx = new DataContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_data);

				List<Node> members = new ArrayList<>();
			
		int _la;
		try {
			setState(76);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(46);
				((DataContext)_localctx).abs = match(T__0);
				setState(47);
				match(T__1);
				setState(48);
				((DataContext)_localctx).TYID = match(TYID);
				setState(49);
				match(T__2);
				setState(58);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ID) {
					{
					setState(56);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
					case 1:
						{
						setState(50);
						((DataContext)_localctx).decl = decl();
						 members.add(((DataContext)_localctx).decl.ast); 
						}
						break;
					case 2:
						{
						setState(53);
						((DataContext)_localctx).fun = fun();
						 members.add(((DataContext)_localctx).fun.ast); 
						}
						break;
					}
					}
					setState(60);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(61);
				match(T__3);

				        	((DataContext)_localctx).ast =  new AbstractDataDecl((((DataContext)_localctx).abs!=null?((DataContext)_localctx).abs.getLine():0), (((DataContext)_localctx).abs!=null?((DataContext)_localctx).abs.getCharPositionInLine():0), (((DataContext)_localctx).TYID!=null?((DataContext)_localctx).TYID.getText():null), members);
				    
				}
				break;
			case T__1:
				enterOuterAlt(_localctx, 2);
				{
				setState(63);
				((DataContext)_localctx).d = match(T__1);
				setState(64);
				((DataContext)_localctx).TYID = match(TYID);
				setState(65);
				match(T__2);
				setState(71);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ID) {
					{
					{
					setState(66);
					((DataContext)_localctx).decl = decl();
					 members.add(((DataContext)_localctx).decl.ast); 
					}
					}
					setState(73);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(74);
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
			setState(78);
			((DeclContext)_localctx).id = match(ID);
			setState(79);
			match(T__4);
			setState(80);
			((DeclContext)_localctx).t = type(0);
			setState(81);
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

	public static class Fun2Context extends ParserRuleContext {
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public CmdContext cmd() {
			return getRuleContext(CmdContext.class,0);
		}
		public ParamsContext params() {
			return getRuleContext(ParamsContext.class,0);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public Fun2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fun2; }
	}

	public final Fun2Context fun2() throws RecognitionException {
		Fun2Context _localctx = new Fun2Context(_ctx, getState());
		enterRule(_localctx, 8, RULE_fun2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(ID);
			setState(85);
			match(T__6);
			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(86);
				params();
				}
			}

			setState(89);
			match(T__7);
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(90);
				match(T__8);
				setState(91);
				type(0);
				setState(96);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(92);
					match(T__9);
					setState(93);
					type(0);
					}
					}
					setState(98);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(101);
			cmd();
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
			setState(103);
			((FunContext)_localctx).fun_def = match(ID);
			setState(104);
			match(T__6);
			setState(106);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(105);
				((FunContext)_localctx).p = params();
				}
			}

			setState(108);
			match(T__7);
			setState(121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(109);
				match(T__8);
				setState(110);
				((FunContext)_localctx).t1 = type(0);
				 members.add(((FunContext)_localctx).t1.ast); 
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(112);
					match(T__9);
					setState(113);
					((FunContext)_localctx).t = type(0);
					 members.add(((FunContext)_localctx).t.ast); 
					}
					}
					setState(120);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(123);
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
			setState(126);
			((ParamsContext)_localctx).id1 = match(ID);
			setState(127);
			match(T__4);
			setState(128);
			((ParamsContext)_localctx).t1 = type(0);
			listParam.add(new Param((((ParamsContext)_localctx).id1!=null?((ParamsContext)_localctx).id1.getText():null), ((ParamsContext)_localctx).t1.ast));
			setState(138);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(130);
				match(T__9);
				setState(131);
				((ParamsContext)_localctx).id2 = match(ID);
				setState(132);
				match(T__4);
				setState(133);
				((ParamsContext)_localctx).t2 = type(0);
				listParam.add(new Param((((ParamsContext)_localctx).id2!=null?((ParamsContext)_localctx).id2.getText():null), ((ParamsContext)_localctx).t2.ast));
				}
				}
				setState(140);
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
			setState(154);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__12:
				{
				setState(144);
				((TypeContext)_localctx).Int1 = match(T__12);
				((TypeContext)_localctx).ast =  new TypeInt((((TypeContext)_localctx).Int1!=null?((TypeContext)_localctx).Int1.getLine():0), (((TypeContext)_localctx).Int1!=null?((TypeContext)_localctx).Int1.getCharPositionInLine():0));
				}
				break;
			case T__13:
				{
				setState(146);
				((TypeContext)_localctx).Char1 = match(T__13);
				((TypeContext)_localctx).ast =  new TypeChar((((TypeContext)_localctx).Char1!=null?((TypeContext)_localctx).Char1.getLine():0), (((TypeContext)_localctx).Char1!=null?((TypeContext)_localctx).Char1.getCharPositionInLine():0));
				}
				break;
			case T__14:
				{
				setState(148);
				((TypeContext)_localctx).Bool1 = match(T__14);
				((TypeContext)_localctx).ast =  new TypeBool((((TypeContext)_localctx).Bool1!=null?((TypeContext)_localctx).Bool1.getLine():0), (((TypeContext)_localctx).Bool1!=null?((TypeContext)_localctx).Bool1.getCharPositionInLine():0));
				}
				break;
			case T__15:
				{
				setState(150);
				((TypeContext)_localctx).Float1 = match(T__15);
				((TypeContext)_localctx).ast =  new TypeFloat((((TypeContext)_localctx).Float1!=null?((TypeContext)_localctx).Float1.getLine():0), (((TypeContext)_localctx).Float1!=null?((TypeContext)_localctx).Float1.getCharPositionInLine():0));
				}
				break;
			case TYID:
				{
				setState(152);
				((TypeContext)_localctx).TYID = match(TYID);
				((TypeContext)_localctx).ast =  new TYID((((TypeContext)_localctx).TYID!=null?((TypeContext)_localctx).TYID.getLine():0), (((TypeContext)_localctx).TYID!=null?((TypeContext)_localctx).TYID.getCharPositionInLine():0), (((TypeContext)_localctx).TYID!=null?((TypeContext)_localctx).TYID.getText():null));
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(162);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
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
					setState(156);
					if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
					setState(157);
					match(T__10);
					setState(158);
					match(T__11);
					((TypeContext)_localctx).ast =  new ArrayType(((TypeContext)_localctx).t1.ast.getLine(), ((TypeContext)_localctx).t1.ast.getCol());
					}
					} 
				}
				setState(164);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
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
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_block);
		List<Cmd> listCmd = new ArrayList<>();
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			match(T__2);
			setState(171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__16) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << ID))) != 0)) {
				{
				{
				setState(166);
				((BlockContext)_localctx).c1 = cmd();
				listCmd.add(((BlockContext)_localctx).c1.ast);
				}
				}
				setState(173);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			((BlockContext)_localctx).ast =  new Block(((BlockContext)_localctx).c1.ast.getLine(), ((BlockContext)_localctx).c1.ast.getCol(), listCmd); 
			setState(175);
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
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmd);

		    	List<LValue> membersLValue = new ArrayList<>();
				List<Expr> membersReturn = new ArrayList<>();
		  	
		int _la;
		try {
			setState(258);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(177);
				((CmdContext)_localctx).block = block();
				 ((CmdContext)_localctx).ast =  ((CmdContext)_localctx).block.ast;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(180);
				((CmdContext)_localctx).ifCond = match(T__16);
				setState(181);
				match(T__6);
				setState(182);
				((CmdContext)_localctx).exp = exp(0);
				setState(183);
				match(T__7);
				setState(184);
				((CmdContext)_localctx).cmd = cmd();
				 ((CmdContext)_localctx).ast =  new CmdIf((((CmdContext)_localctx).ifCond!=null?((CmdContext)_localctx).ifCond.getLine():0), (((CmdContext)_localctx).ifCond!=null?((CmdContext)_localctx).ifCond.getCharPositionInLine():0), ((CmdContext)_localctx).exp.ast, ((CmdContext)_localctx).cmd.ast);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(187);
				((CmdContext)_localctx).ifCond = match(T__16);
				setState(188);
				match(T__6);
				setState(189);
				((CmdContext)_localctx).exp = exp(0);
				setState(190);
				match(T__7);
				setState(191);
				((CmdContext)_localctx).cmd1 = cmd();
				setState(192);
				match(T__17);
				setState(193);
				((CmdContext)_localctx).cmd2 = cmd();
				 ((CmdContext)_localctx).ast =  new CmdIf((((CmdContext)_localctx).ifCond!=null?((CmdContext)_localctx).ifCond.getLine():0), (((CmdContext)_localctx).ifCond!=null?((CmdContext)_localctx).ifCond.getCharPositionInLine():0), ((CmdContext)_localctx).exp.ast, ((CmdContext)_localctx).cmd1.ast, ((CmdContext)_localctx).cmd2.ast );
						
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(196);
				((CmdContext)_localctx).it = match(T__18);
				setState(197);
				match(T__6);
				setState(198);
				((CmdContext)_localctx).itcond = itcond();
				setState(199);
				match(T__7);
				setState(200);
				((CmdContext)_localctx).cmd = cmd();
				 ((CmdContext)_localctx).ast =  new CmdIterate((((CmdContext)_localctx).it!=null?((CmdContext)_localctx).it.getLine():0), (((CmdContext)_localctx).it!=null?((CmdContext)_localctx).it.getCharPositionInLine():0), ((CmdContext)_localctx).itcond.ast, ((CmdContext)_localctx).cmd.ast );
						
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(203);
				((CmdContext)_localctx).rd = match(T__19);
				setState(204);
				((CmdContext)_localctx).lvalue = lvalue(0);
				setState(205);
				match(T__5);
				 ((CmdContext)_localctx).ast =  new CmdRead((((CmdContext)_localctx).rd!=null?((CmdContext)_localctx).rd.getLine():0), (((CmdContext)_localctx).rd!=null?((CmdContext)_localctx).rd.getCharPositionInLine():0), ((CmdContext)_localctx).lvalue.ast); 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(208);
				((CmdContext)_localctx).prt = match(T__20);
				setState(209);
				((CmdContext)_localctx).exp = exp(0);
				setState(210);
				match(T__5);
				 ((CmdContext)_localctx).ast =  new CmdPrint((((CmdContext)_localctx).prt!=null?((CmdContext)_localctx).prt.getLine():0), (((CmdContext)_localctx).prt!=null?((CmdContext)_localctx).prt.getCharPositionInLine():0), ((CmdContext)_localctx).exp.ast); 
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(213);
				((CmdContext)_localctx).rt = match(T__21);
				setState(214);
				((CmdContext)_localctx).exp = exp(0);
				 membersReturn.add(((CmdContext)_localctx).exp.ast) ;
				setState(222);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(216);
					match(T__9);
					setState(217);
					((CmdContext)_localctx).exp = exp(0);
					membersReturn.add(((CmdContext)_localctx).exp.ast);
					}
					}
					setState(224);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				 ((CmdContext)_localctx).ast =  new CmdReturn((((CmdContext)_localctx).rt!=null?((CmdContext)_localctx).rt.getLine():0), (((CmdContext)_localctx).rt!=null?((CmdContext)_localctx).rt.getCharPositionInLine():0), membersReturn);
				setState(226);
				match(T__5);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(228);
				((CmdContext)_localctx).lvalue = lvalue(0);
				setState(229);
				match(T__22);
				setState(230);
				((CmdContext)_localctx).exp = exp(0);
				setState(231);
				match(T__5);
				 ((CmdContext)_localctx).ast =  new CmdAssign(((CmdContext)_localctx).lvalue.ast.getLine(), ((CmdContext)_localctx).lvalue.ast.getCol(), ((CmdContext)_localctx).lvalue.ast, ((CmdContext)_localctx).exp.ast);
						
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(234);
				((CmdContext)_localctx).ID = match(ID);
				setState(235);
				match(T__6);
				setState(237);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__29) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << INT) | (1L << FLOAT) | (1L << CHAR) | (1L << ID))) != 0)) {
					{
					setState(236);
					((CmdContext)_localctx).exps = exps();
					}
				}

				setState(239);
				match(T__7);
				setState(255);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__23) {
					{
					setState(240);
					match(T__23);
					setState(241);
					((CmdContext)_localctx).l1 = lvalue(0);
					 membersLValue.add(((CmdContext)_localctx).l1.ast) ;
					setState(249);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__9) {
						{
						{
						setState(243);
						match(T__9);
						setState(244);
						((CmdContext)_localctx).l2 = lvalue(0);
						 membersLValue.add(((CmdContext)_localctx).l2.ast) ;
						}
						}
						setState(251);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(252);
					match(T__24);
					((CmdContext)_localctx).ast =  new CmdFuncCall((((CmdContext)_localctx).ID!=null?((CmdContext)_localctx).ID.getLine():0), (((CmdContext)_localctx).ID!=null?((CmdContext)_localctx).ID.getCharPositionInLine():0),(((CmdContext)_localctx).ID!=null?((CmdContext)_localctx).ID.getText():null), ((CmdContext)_localctx).exps != null ? ((CmdContext)_localctx).exps.ast : null, membersLValue );
								
					}
				}

				setState(257);
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
		enterRule(_localctx, 20, RULE_itcond);
		try {
			setState(268);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(260);
				((ItcondContext)_localctx).ID = match(ID);
				setState(261);
				match(T__8);
				setState(262);
				((ItcondContext)_localctx).exp = exp(0);
				 ((ItcondContext)_localctx).ast =  new IdItCond((((ItcondContext)_localctx).ID!=null?((ItcondContext)_localctx).ID.getLine():0), (((ItcondContext)_localctx).ID!=null?((ItcondContext)_localctx).ID.getCharPositionInLine():0), (((ItcondContext)_localctx).ID!=null?((ItcondContext)_localctx).ID.getText():null), ((ItcondContext)_localctx).exp.ast);
						
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(265);
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
	}

	public final ExpContext exp() throws RecognitionException {
		return exp(0);
	}

	private ExpContext exp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpContext _localctx = new ExpContext(_ctx, _parentState);
		ExpContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_exp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(320);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(271);
				((ExpContext)_localctx).not = match(T__33);
				setState(272);
				((ExpContext)_localctx).exp = exp(12);
				 ((ExpContext)_localctx).ast =  new NotExpr((((ExpContext)_localctx).not!=null?((ExpContext)_localctx).not.getLine():0), (((ExpContext)_localctx).not!=null?((ExpContext)_localctx).not.getCharPositionInLine():0), ((ExpContext)_localctx).exp.ast);
				}
				break;
			case 2:
				{
				setState(275);
				((ExpContext)_localctx).minus = match(T__29);
				setState(276);
				((ExpContext)_localctx).exp = exp(11);
				 ((ExpContext)_localctx).ast =  new MinusExpr((((ExpContext)_localctx).minus!=null?((ExpContext)_localctx).minus.getLine():0), (((ExpContext)_localctx).minus!=null?((ExpContext)_localctx).minus.getCharPositionInLine():0), ((ExpContext)_localctx).exp.ast);
				}
				break;
			case 3:
				{
				setState(279);
				((ExpContext)_localctx).lvalue = lvalue(0);
				 ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).lvalue.ast;
						
				}
				break;
			case 4:
				{
				setState(282);
				match(T__6);
				setState(283);
				((ExpContext)_localctx).exp = exp(0);
				setState(284);
				match(T__7);
				 ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).exp.ast; 
				}
				break;
			case 5:
				{
				setState(287);
				match(T__34);
				setState(288);
				((ExpContext)_localctx).type = type(0);
				setState(293);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(289);
					match(T__10);
					setState(290);
					((ExpContext)_localctx).e = ((ExpContext)_localctx).exp = exp(0);
					setState(291);
					match(T__11);
					}
					break;
				}
				 ((ExpContext)_localctx).ast =  new VarExpr(((ExpContext)_localctx).exp.ast.getLine(), ((ExpContext)_localctx).exp.ast.getCol(), ((ExpContext)_localctx).type.ast, ((ExpContext)_localctx).e != null ? ((ExpContext)_localctx).e.ast : null);
						
				}
				break;
			case 6:
				{
				setState(297);
				((ExpContext)_localctx).ID = match(ID);
				setState(298);
				match(T__6);
				setState(300);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__29) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << INT) | (1L << FLOAT) | (1L << CHAR) | (1L << ID))) != 0)) {
					{
					setState(299);
					((ExpContext)_localctx).exps = exps();
					}
				}

				setState(302);
				match(T__7);
				setState(303);
				match(T__10);
				setState(304);
				((ExpContext)_localctx).exp = exp(0);
				setState(305);
				match(T__11);
				 ((ExpContext)_localctx).ast =  new CallFunctionAccess((((ExpContext)_localctx).ID!=null?((ExpContext)_localctx).ID.getLine():0), (((ExpContext)_localctx).ID!=null?((ExpContext)_localctx).ID.getCharPositionInLine():0), (((ExpContext)_localctx).ID!=null?((ExpContext)_localctx).ID.getText():null), ((ExpContext)_localctx).exps !=null ? ((ExpContext)_localctx).exps.ast : null , ((ExpContext)_localctx).exp.ast);
						
				}
				break;
			case 7:
				{
				setState(308);
				((ExpContext)_localctx).t = match(T__35);
				 ((ExpContext)_localctx).ast =  new TrueValue((((ExpContext)_localctx).t!=null?((ExpContext)_localctx).t.getLine():0), (((ExpContext)_localctx).t!=null?((ExpContext)_localctx).t.getCharPositionInLine():0));
				}
				break;
			case 8:
				{
				setState(310);
				((ExpContext)_localctx).f = match(T__36);
				 ((ExpContext)_localctx).ast =  new FalseValue((((ExpContext)_localctx).f!=null?((ExpContext)_localctx).f.getLine():0), (((ExpContext)_localctx).f!=null?((ExpContext)_localctx).f.getCharPositionInLine():0));
				}
				break;
			case 9:
				{
				setState(312);
				((ExpContext)_localctx).n = match(T__37);
				 ((ExpContext)_localctx).ast =  new NullValue((((ExpContext)_localctx).n!=null?((ExpContext)_localctx).n.getLine():0), (((ExpContext)_localctx).n!=null?((ExpContext)_localctx).n.getCharPositionInLine():0));
				}
				break;
			case 10:
				{
				setState(314);
				((ExpContext)_localctx).INT = match(INT);
				 ((ExpContext)_localctx).ast =  new IntValue((((ExpContext)_localctx).INT!=null?((ExpContext)_localctx).INT.getLine():0), (((ExpContext)_localctx).INT!=null?((ExpContext)_localctx).INT.getCharPositionInLine():0), (((ExpContext)_localctx).INT!=null?((ExpContext)_localctx).INT.getText():null));
				}
				break;
			case 11:
				{
				setState(316);
				((ExpContext)_localctx).FLOAT = match(FLOAT);
				 ((ExpContext)_localctx).ast =  new FloatValue((((ExpContext)_localctx).FLOAT!=null?((ExpContext)_localctx).FLOAT.getLine():0), (((ExpContext)_localctx).FLOAT!=null?((ExpContext)_localctx).FLOAT.getCharPositionInLine():0), (((ExpContext)_localctx).FLOAT!=null?((ExpContext)_localctx).FLOAT.getText():null));
				}
				break;
			case 12:
				{
				setState(318);
				((ExpContext)_localctx).CHAR = match(CHAR);
				 ((ExpContext)_localctx).ast =  new CharValue((((ExpContext)_localctx).CHAR!=null?((ExpContext)_localctx).CHAR.getLine():0), (((ExpContext)_localctx).CHAR!=null?((ExpContext)_localctx).CHAR.getCharPositionInLine():0), (((ExpContext)_localctx).CHAR!=null?((ExpContext)_localctx).CHAR.getText():null));
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(369);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(367);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
					case 1:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						_localctx.exp1 = _prevctx;
						_localctx.exp1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(322);
						if (!(precpred(_ctx, 21))) throw new FailedPredicateException(this, "precpred(_ctx, 21)");
						setState(323);
						((ExpContext)_localctx).op = match(T__25);
						setState(324);
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
						setState(327);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(328);
						((ExpContext)_localctx).op = match(T__26);
						setState(329);
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
						setState(332);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(333);
						((ExpContext)_localctx).op = match(T__27);
						setState(334);
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
						setState(337);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(338);
						((ExpContext)_localctx).op = match(T__28);
						setState(339);
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
						setState(342);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(343);
						((ExpContext)_localctx).op = match(T__29);
						setState(344);
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
						setState(347);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(348);
						((ExpContext)_localctx).op = match(T__23);
						setState(349);
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
						setState(352);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(353);
						((ExpContext)_localctx).op = match(T__30);
						setState(354);
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
						setState(357);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(358);
						((ExpContext)_localctx).op = match(T__31);
						setState(359);
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
						setState(362);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(363);
						((ExpContext)_localctx).op = match(T__32);
						setState(364);
						((ExpContext)_localctx).exp2 = ((ExpContext)_localctx).exp = exp(14);
						 ((ExpContext)_localctx).ast =  new And(((ExpContext)_localctx).exp1.ast.getLine(), ((ExpContext)_localctx).exp1.ast.getCol(), ((ExpContext)_localctx).exp1.ast, ((ExpContext)_localctx).exp2.ast);
						}
						break;
					}
					} 
				}
				setState(371);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
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
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_lvalue, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(373);
			((LvalueContext)_localctx).ID = match(ID);
			 ((LvalueContext)_localctx).ast =  new ID((((LvalueContext)_localctx).ID!=null?((LvalueContext)_localctx).ID.getLine():0), (((LvalueContext)_localctx).ID!=null?((LvalueContext)_localctx).ID.getCharPositionInLine():0), (((LvalueContext)_localctx).ID!=null?((LvalueContext)_localctx).ID.getText():null));
			}
			_ctx.stop = _input.LT(-1);
			setState(388);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(386);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
					case 1:
						{
						_localctx = new LvalueContext(_parentctx, _parentState);
						_localctx.l1 = _prevctx;
						_localctx.l1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_lvalue);
						setState(376);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(377);
						match(T__10);
						setState(378);
						((LvalueContext)_localctx).exp = exp(0);
						setState(379);
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
						setState(382);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(383);
						match(T__38);
						setState(384);
						((LvalueContext)_localctx).ID = match(ID);
						((LvalueContext)_localctx).ast =  new IdLValue(((LvalueContext)_localctx).l1.ast.getLine(), ((LvalueContext)_localctx).l1.ast.getCol(), ((LvalueContext)_localctx).l1.ast, (((LvalueContext)_localctx).ID!=null?((LvalueContext)_localctx).ID.getText():null));
						}
						break;
					}
					} 
				}
				setState(390);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
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
		enterRule(_localctx, 26, RULE_exps);

		    	List<Expr> expsValues = new ArrayList<>();
		  	
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(391);
			((ExpsContext)_localctx).e1 = exp(0);
			expsValues.add(((ExpsContext)_localctx).e1.ast);
			setState(399);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(393);
				match(T__9);
				setState(394);
				((ExpsContext)_localctx).e2 = exp(0);
				expsValues.add(((ExpsContext)_localctx).e2.ast);
				}
				}
				setState(401);
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
		case 11:
			return exp_sempred((ExpContext)_localctx, predIndex);
		case 12:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\61\u0197\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\7\2\"\n\2\f\2\16"+
		"\2%\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\5\3/\n\3\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\7\4;\n\4\f\4\16\4>\13\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\7\4H\n\4\f\4\16\4K\13\4\3\4\3\4\5\4O\n\4\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\6\3\6\3\6\5\6Z\n\6\3\6\3\6\3\6\3\6\3\6\7\6a\n\6\f\6\16\6d\13\6\5"+
		"\6f\n\6\3\6\3\6\3\7\3\7\3\7\5\7m\n\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7"+
		"\7w\n\7\f\7\16\7z\13\7\5\7|\n\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\7\b\u008b\n\b\f\b\16\b\u008e\13\b\3\b\3\b\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u009d\n\t\3\t\3\t\3\t\3\t\7\t\u00a3"+
		"\n\t\f\t\16\t\u00a6\13\t\3\n\3\n\3\n\3\n\7\n\u00ac\n\n\f\n\16\n\u00af"+
		"\13\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\7\13\u00df\n\13\f\13\16\13\u00e2\13\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00f0\n\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00fa\n\13\f\13\16\13\u00fd"+
		"\13\13\3\13\3\13\3\13\5\13\u0102\n\13\3\13\5\13\u0105\n\13\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\5\f\u010f\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u0128\n\r"+
		"\3\r\3\r\3\r\3\r\3\r\5\r\u012f\n\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u0143\n\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\7\r\u0172\n\r\f\r\16\r\u0175\13\r\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u0185\n\16"+
		"\f\16\16\16\u0188\13\16\3\17\3\17\3\17\3\17\3\17\3\17\7\17\u0190\n\17"+
		"\f\17\16\17\u0193\13\17\3\17\3\17\3\17\2\5\20\30\32\20\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\2\2\2\u01c1\2#\3\2\2\2\4.\3\2\2\2\6N\3\2\2\2\bP"+
		"\3\2\2\2\nV\3\2\2\2\fi\3\2\2\2\16\u0080\3\2\2\2\20\u009c\3\2\2\2\22\u00a7"+
		"\3\2\2\2\24\u0104\3\2\2\2\26\u010e\3\2\2\2\30\u0142\3\2\2\2\32\u0176\3"+
		"\2\2\2\34\u0189\3\2\2\2\36\37\5\4\3\2\37 \b\2\1\2 \"\3\2\2\2!\36\3\2\2"+
		"\2\"%\3\2\2\2#!\3\2\2\2#$\3\2\2\2$&\3\2\2\2%#\3\2\2\2&\'\b\2\1\2\'\3\3"+
		"\2\2\2()\5\6\4\2)*\b\3\1\2*/\3\2\2\2+,\5\f\7\2,-\b\3\1\2-/\3\2\2\2.(\3"+
		"\2\2\2.+\3\2\2\2/\5\3\2\2\2\60\61\7\3\2\2\61\62\7\4\2\2\62\63\7.\2\2\63"+
		"<\7\5\2\2\64\65\5\b\5\2\65\66\b\4\1\2\66;\3\2\2\2\678\5\f\7\289\b\4\1"+
		"\29;\3\2\2\2:\64\3\2\2\2:\67\3\2\2\2;>\3\2\2\2<:\3\2\2\2<=\3\2\2\2=?\3"+
		"\2\2\2><\3\2\2\2?@\7\6\2\2@O\b\4\1\2AB\7\4\2\2BC\7.\2\2CI\7\5\2\2DE\5"+
		"\b\5\2EF\b\4\1\2FH\3\2\2\2GD\3\2\2\2HK\3\2\2\2IG\3\2\2\2IJ\3\2\2\2JL\3"+
		"\2\2\2KI\3\2\2\2LM\7\6\2\2MO\b\4\1\2N\60\3\2\2\2NA\3\2\2\2O\7\3\2\2\2"+
		"PQ\7-\2\2QR\7\7\2\2RS\5\20\t\2ST\7\b\2\2TU\b\5\1\2U\t\3\2\2\2VW\7-\2\2"+
		"WY\7\t\2\2XZ\5\16\b\2YX\3\2\2\2YZ\3\2\2\2Z[\3\2\2\2[e\7\n\2\2\\]\7\13"+
		"\2\2]b\5\20\t\2^_\7\f\2\2_a\5\20\t\2`^\3\2\2\2ad\3\2\2\2b`\3\2\2\2bc\3"+
		"\2\2\2cf\3\2\2\2db\3\2\2\2e\\\3\2\2\2ef\3\2\2\2fg\3\2\2\2gh\5\24\13\2"+
		"h\13\3\2\2\2ij\7-\2\2jl\7\t\2\2km\5\16\b\2lk\3\2\2\2lm\3\2\2\2mn\3\2\2"+
		"\2n{\7\n\2\2op\7\13\2\2pq\5\20\t\2qx\b\7\1\2rs\7\f\2\2st\5\20\t\2tu\b"+
		"\7\1\2uw\3\2\2\2vr\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy\3\2\2\2y|\3\2\2\2zx\3"+
		"\2\2\2{o\3\2\2\2{|\3\2\2\2|}\3\2\2\2}~\5\24\13\2~\177\b\7\1\2\177\r\3"+
		"\2\2\2\u0080\u0081\7-\2\2\u0081\u0082\7\7\2\2\u0082\u0083\5\20\t\2\u0083"+
		"\u008c\b\b\1\2\u0084\u0085\7\f\2\2\u0085\u0086\7-\2\2\u0086\u0087\7\7"+
		"\2\2\u0087\u0088\5\20\t\2\u0088\u0089\b\b\1\2\u0089\u008b\3\2\2\2\u008a"+
		"\u0084\3\2\2\2\u008b\u008e\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2"+
		"\2\2\u008d\u008f\3\2\2\2\u008e\u008c\3\2\2\2\u008f\u0090\b\b\1\2\u0090"+
		"\17\3\2\2\2\u0091\u0092\b\t\1\2\u0092\u0093\7\17\2\2\u0093\u009d\b\t\1"+
		"\2\u0094\u0095\7\20\2\2\u0095\u009d\b\t\1\2\u0096\u0097\7\21\2\2\u0097"+
		"\u009d\b\t\1\2\u0098\u0099\7\22\2\2\u0099\u009d\b\t\1\2\u009a\u009b\7"+
		".\2\2\u009b\u009d\b\t\1\2\u009c\u0091\3\2\2\2\u009c\u0094\3\2\2\2\u009c"+
		"\u0096\3\2\2\2\u009c\u0098\3\2\2\2\u009c\u009a\3\2\2\2\u009d\u00a4\3\2"+
		"\2\2\u009e\u009f\f\b\2\2\u009f\u00a0\7\r\2\2\u00a0\u00a1\7\16\2\2\u00a1"+
		"\u00a3\b\t\1\2\u00a2\u009e\3\2\2\2\u00a3\u00a6\3\2\2\2\u00a4\u00a2\3\2"+
		"\2\2\u00a4\u00a5\3\2\2\2\u00a5\21\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a7\u00ad"+
		"\7\5\2\2\u00a8\u00a9\5\24\13\2\u00a9\u00aa\b\n\1\2\u00aa\u00ac\3\2\2\2"+
		"\u00ab\u00a8\3\2\2\2\u00ac\u00af\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae"+
		"\3\2\2\2\u00ae\u00b0\3\2\2\2\u00af\u00ad\3\2\2\2\u00b0\u00b1\b\n\1\2\u00b1"+
		"\u00b2\7\6\2\2\u00b2\23\3\2\2\2\u00b3\u00b4\5\22\n\2\u00b4\u00b5\b\13"+
		"\1\2\u00b5\u0105\3\2\2\2\u00b6\u00b7\7\23\2\2\u00b7\u00b8\7\t\2\2\u00b8"+
		"\u00b9\5\30\r\2\u00b9\u00ba\7\n\2\2\u00ba\u00bb\5\24\13\2\u00bb\u00bc"+
		"\b\13\1\2\u00bc\u0105\3\2\2\2\u00bd\u00be\7\23\2\2\u00be\u00bf\7\t\2\2"+
		"\u00bf\u00c0\5\30\r\2\u00c0\u00c1\7\n\2\2\u00c1\u00c2\5\24\13\2\u00c2"+
		"\u00c3\7\24\2\2\u00c3\u00c4\5\24\13\2\u00c4\u00c5\b\13\1\2\u00c5\u0105"+
		"\3\2\2\2\u00c6\u00c7\7\25\2\2\u00c7\u00c8\7\t\2\2\u00c8\u00c9\5\26\f\2"+
		"\u00c9\u00ca\7\n\2\2\u00ca\u00cb\5\24\13\2\u00cb\u00cc\b\13\1\2\u00cc"+
		"\u0105\3\2\2\2\u00cd\u00ce\7\26\2\2\u00ce\u00cf\5\32\16\2\u00cf\u00d0"+
		"\7\b\2\2\u00d0\u00d1\b\13\1\2\u00d1\u0105\3\2\2\2\u00d2\u00d3\7\27\2\2"+
		"\u00d3\u00d4\5\30\r\2\u00d4\u00d5\7\b\2\2\u00d5\u00d6\b\13\1\2\u00d6\u0105"+
		"\3\2\2\2\u00d7\u00d8\7\30\2\2\u00d8\u00d9\5\30\r\2\u00d9\u00e0\b\13\1"+
		"\2\u00da\u00db\7\f\2\2\u00db\u00dc\5\30\r\2\u00dc\u00dd\b\13\1\2\u00dd"+
		"\u00df\3\2\2\2\u00de\u00da\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0\u00de\3\2"+
		"\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00e3\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e3"+
		"\u00e4\b\13\1\2\u00e4\u00e5\7\b\2\2\u00e5\u0105\3\2\2\2\u00e6\u00e7\5"+
		"\32\16\2\u00e7\u00e8\7\31\2\2\u00e8\u00e9\5\30\r\2\u00e9\u00ea\7\b\2\2"+
		"\u00ea\u00eb\b\13\1\2\u00eb\u0105\3\2\2\2\u00ec\u00ed\7-\2\2\u00ed\u00ef"+
		"\7\t\2\2\u00ee\u00f0\5\34\17\2\u00ef\u00ee\3\2\2\2\u00ef\u00f0\3\2\2\2"+
		"\u00f0\u00f1\3\2\2\2\u00f1\u0101\7\n\2\2\u00f2\u00f3\7\32\2\2\u00f3\u00f4"+
		"\5\32\16\2\u00f4\u00fb\b\13\1\2\u00f5\u00f6\7\f\2\2\u00f6\u00f7\5\32\16"+
		"\2\u00f7\u00f8\b\13\1\2\u00f8\u00fa\3\2\2\2\u00f9\u00f5\3\2\2\2\u00fa"+
		"\u00fd\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fe\3\2"+
		"\2\2\u00fd\u00fb\3\2\2\2\u00fe\u00ff\7\33\2\2\u00ff\u0100\b\13\1\2\u0100"+
		"\u0102\3\2\2\2\u0101\u00f2\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0103\3\2"+
		"\2\2\u0103\u0105\7\b\2\2\u0104\u00b3\3\2\2\2\u0104\u00b6\3\2\2\2\u0104"+
		"\u00bd\3\2\2\2\u0104\u00c6\3\2\2\2\u0104\u00cd\3\2\2\2\u0104\u00d2\3\2"+
		"\2\2\u0104\u00d7\3\2\2\2\u0104\u00e6\3\2\2\2\u0104\u00ec\3\2\2\2\u0105"+
		"\25\3\2\2\2\u0106\u0107\7-\2\2\u0107\u0108\7\13\2\2\u0108\u0109\5\30\r"+
		"\2\u0109\u010a\b\f\1\2\u010a\u010f\3\2\2\2\u010b\u010c\5\30\r\2\u010c"+
		"\u010d\b\f\1\2\u010d\u010f\3\2\2\2\u010e\u0106\3\2\2\2\u010e\u010b\3\2"+
		"\2\2\u010f\27\3\2\2\2\u0110\u0111\b\r\1\2\u0111\u0112\7$\2\2\u0112\u0113"+
		"\5\30\r\16\u0113\u0114\b\r\1\2\u0114\u0143\3\2\2\2\u0115\u0116\7 \2\2"+
		"\u0116\u0117\5\30\r\r\u0117\u0118\b\r\1\2\u0118\u0143\3\2\2\2\u0119\u011a"+
		"\5\32\16\2\u011a\u011b\b\r\1\2\u011b\u0143\3\2\2\2\u011c\u011d\7\t\2\2"+
		"\u011d\u011e\5\30\r\2\u011e\u011f\7\n\2\2\u011f\u0120\b\r\1\2\u0120\u0143"+
		"\3\2\2\2\u0121\u0122\7%\2\2\u0122\u0127\5\20\t\2\u0123\u0124\7\r\2\2\u0124"+
		"\u0125\5\30\r\2\u0125\u0126\7\16\2\2\u0126\u0128\3\2\2\2\u0127\u0123\3"+
		"\2\2\2\u0127\u0128\3\2\2\2\u0128\u0129\3\2\2\2\u0129\u012a\b\r\1\2\u012a"+
		"\u0143\3\2\2\2\u012b\u012c\7-\2\2\u012c\u012e\7\t\2\2\u012d\u012f\5\34"+
		"\17\2\u012e\u012d\3\2\2\2\u012e\u012f\3\2\2\2\u012f\u0130\3\2\2\2\u0130"+
		"\u0131\7\n\2\2\u0131\u0132\7\r\2\2\u0132\u0133\5\30\r\2\u0133\u0134\7"+
		"\16\2\2\u0134\u0135\b\r\1\2\u0135\u0143\3\2\2\2\u0136\u0137\7&\2\2\u0137"+
		"\u0143\b\r\1\2\u0138\u0139\7\'\2\2\u0139\u0143\b\r\1\2\u013a\u013b\7("+
		"\2\2\u013b\u0143\b\r\1\2\u013c\u013d\7*\2\2\u013d\u0143\b\r\1\2\u013e"+
		"\u013f\7+\2\2\u013f\u0143\b\r\1\2\u0140\u0141\7,\2\2\u0141\u0143\b\r\1"+
		"\2\u0142\u0110\3\2\2\2\u0142\u0115\3\2\2\2\u0142\u0119\3\2\2\2\u0142\u011c"+
		"\3\2\2\2\u0142\u0121\3\2\2\2\u0142\u012b\3\2\2\2\u0142\u0136\3\2\2\2\u0142"+
		"\u0138\3\2\2\2\u0142\u013a\3\2\2\2\u0142\u013c\3\2\2\2\u0142\u013e\3\2"+
		"\2\2\u0142\u0140\3\2\2\2\u0143\u0173\3\2\2\2\u0144\u0145\f\27\2\2\u0145"+
		"\u0146\7\34\2\2\u0146\u0147\5\30\r\30\u0147\u0148\b\r\1\2\u0148\u0172"+
		"\3\2\2\2\u0149\u014a\f\26\2\2\u014a\u014b\7\35\2\2\u014b\u014c\5\30\r"+
		"\27\u014c\u014d\b\r\1\2\u014d\u0172\3\2\2\2\u014e\u014f\f\25\2\2\u014f"+
		"\u0150\7\36\2\2\u0150\u0151\5\30\r\26\u0151\u0152\b\r\1\2\u0152\u0172"+
		"\3\2\2\2\u0153\u0154\f\24\2\2\u0154\u0155\7\37\2\2\u0155\u0156\5\30\r"+
		"\25\u0156\u0157\b\r\1\2\u0157\u0172\3\2\2\2\u0158\u0159\f\23\2\2\u0159"+
		"\u015a\7 \2\2\u015a\u015b\5\30\r\24\u015b\u015c\b\r\1\2\u015c\u0172\3"+
		"\2\2\2\u015d\u015e\f\22\2\2\u015e\u015f\7\32\2\2\u015f\u0160\5\30\r\23"+
		"\u0160\u0161\b\r\1\2\u0161\u0172\3\2\2\2\u0162\u0163\f\21\2\2\u0163\u0164"+
		"\7!\2\2\u0164\u0165\5\30\r\22\u0165\u0166\b\r\1\2\u0166\u0172\3\2\2\2"+
		"\u0167\u0168\f\20\2\2\u0168\u0169\7\"\2\2\u0169\u016a\5\30\r\21\u016a"+
		"\u016b\b\r\1\2\u016b\u0172\3\2\2\2\u016c\u016d\f\17\2\2\u016d\u016e\7"+
		"#\2\2\u016e\u016f\5\30\r\20\u016f\u0170\b\r\1\2\u0170\u0172\3\2\2\2\u0171"+
		"\u0144\3\2\2\2\u0171\u0149\3\2\2\2\u0171\u014e\3\2\2\2\u0171\u0153\3\2"+
		"\2\2\u0171\u0158\3\2\2\2\u0171\u015d\3\2\2\2\u0171\u0162\3\2\2\2\u0171"+
		"\u0167\3\2\2\2\u0171\u016c\3\2\2\2\u0172\u0175\3\2\2\2\u0173\u0171\3\2"+
		"\2\2\u0173\u0174\3\2\2\2\u0174\31\3\2\2\2\u0175\u0173\3\2\2\2\u0176\u0177"+
		"\b\16\1\2\u0177\u0178\7-\2\2\u0178\u0179\b\16\1\2\u0179\u0186\3\2\2\2"+
		"\u017a\u017b\f\4\2\2\u017b\u017c\7\r\2\2\u017c\u017d\5\30\r\2\u017d\u017e"+
		"\7\16\2\2\u017e\u017f\b\16\1\2\u017f\u0185\3\2\2\2\u0180\u0181\f\3\2\2"+
		"\u0181\u0182\7)\2\2\u0182\u0183\7-\2\2\u0183\u0185\b\16\1\2\u0184\u017a"+
		"\3\2\2\2\u0184\u0180\3\2\2\2\u0185\u0188\3\2\2\2\u0186\u0184\3\2\2\2\u0186"+
		"\u0187\3\2\2\2\u0187\33\3\2\2\2\u0188\u0186\3\2\2\2\u0189\u018a\5\30\r"+
		"\2\u018a\u0191\b\17\1\2\u018b\u018c\7\f\2\2\u018c\u018d\5\30\r\2\u018d"+
		"\u018e\b\17\1\2\u018e\u0190\3\2\2\2\u018f\u018b\3\2\2\2\u0190\u0193\3"+
		"\2\2\2\u0191\u018f\3\2\2\2\u0191\u0192\3\2\2\2\u0192\u0194\3\2\2\2\u0193"+
		"\u0191\3\2\2\2\u0194\u0195\b\17\1\2\u0195\35\3\2\2\2 #.:<INYbelx{\u008c"+
		"\u009c\u00a4\u00ad\u00e0\u00ef\u00fb\u0101\u0104\u010e\u0127\u012e\u0142"+
		"\u0171\u0173\u0184\u0186\u0191";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}