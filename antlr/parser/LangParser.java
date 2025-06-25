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
		RULE_params = 5, RULE_type = 6, RULE_typeLinha = 7, RULE_btype = 8, RULE_block = 9, 
		RULE_cmd = 10, RULE_itcond = 11, RULE_exp = 12, RULE_expLinha = 13, RULE_op = 14, 
		RULE_lvalue = 15, RULE_lvalueLinha = 16, RULE_exps = 17;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "def", "data", "decl", "fun", "params", "type", "typeLinha", 
			"btype", "block", "cmd", "itcond", "exp", "expLinha", "op", "lvalue", 
			"lvalueLinha", "exps"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'abstract'", "'data'", "'{'", "'}'", "'::'", "';'", "'('", "')'", 
			"':'", "','", "'['", "']'", "'Int'", "'Char'", "'Bool'", "'Float'", "'if'", 
			"'else'", "'iterate'", "'read'", "'print'", "'return'", "'='", "'<'", 
			"'>'", "'!'", "'-'", "'new'", "'true'", "'false'", "'null'", "'&&'", 
			"'=='", "'!='", "'+'", "'*'", "'/'", "'%'", "'.'"
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0 || _la==ID) {
				{
				{
				setState(36);
				def();
				}
				}
				setState(41);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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
			setState(44);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				enterOuterAlt(_localctx, 1);
				{
				setState(42);
				data(0);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(43);
				fun();
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
		public DataContext data() {
			return getRuleContext(DataContext.class,0);
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
		return data(0);
	}

	private DataContext data(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		DataContext _localctx = new DataContext(_ctx, _parentState);
		DataContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_data, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(47);
			match(T__0);
			setState(48);
			match(T__1);
			setState(49);
			match(TYID);
			setState(50);
			match(T__2);
			setState(55);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				setState(53);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(51);
					decl();
					}
					break;
				case 2:
					{
					setState(52);
					fun();
					}
					break;
				}
				}
				setState(57);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(58);
			match(T__3);
			}
			_ctx.stop = _input.LT(-1);
			setState(72);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new DataContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_data);
					setState(60);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(61);
					match(TYID);
					setState(62);
					match(T__2);
					setState(66);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==ID) {
						{
						{
						setState(63);
						decl();
						}
						}
						setState(68);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(69);
					match(T__3);
					}
					} 
				}
				setState(74);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
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

	public static class DeclContext extends ParserRuleContext {
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
			setState(75);
			match(ID);
			setState(76);
			match(T__4);
			setState(77);
			type();
			setState(78);
			match(T__5);
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(ID);
			setState(81);
			match(T__6);
			setState(83);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(82);
				params();
				}
			}

			setState(85);
			match(T__7);
			setState(95);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(86);
				match(T__8);
				setState(87);
				type();
				setState(92);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(88);
					match(T__9);
					setState(89);
					type();
					}
					}
					setState(94);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(97);
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

	public static class ParamsContext extends ParserRuleContext {
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
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(ID);
			setState(100);
			match(T__4);
			setState(101);
			type();
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(102);
				match(T__9);
				setState(103);
				match(ID);
				setState(104);
				match(T__4);
				setState(105);
				type();
				}
				}
				setState(110);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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
		public BtypeContext btype() {
			return getRuleContext(BtypeContext.class,0);
		}
		public TypeLinhaContext typeLinha() {
			return getRuleContext(TypeLinhaContext.class,0);
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
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_type);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			btype();
			setState(112);
			typeLinha();
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

	public static class TypeLinhaContext extends ParserRuleContext {
		public TypeLinhaContext typeLinha() {
			return getRuleContext(TypeLinhaContext.class,0);
		}
		public TypeLinhaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeLinha; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterTypeLinha(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitTypeLinha(this);
		}
	}

	public final TypeLinhaContext typeLinha() throws RecognitionException {
		TypeLinhaContext _localctx = new TypeLinhaContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_typeLinha);
		try {
			setState(118);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(114);
				match(T__10);
				setState(115);
				match(T__11);
				setState(116);
				typeLinha();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
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

	public static class BtypeContext extends ParserRuleContext {
		public TerminalNode TYID() { return getToken(LangParser.TYID, 0); }
		public BtypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_btype; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterBtype(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitBtype(this);
		}
	}

	public final BtypeContext btype() throws RecognitionException {
		BtypeContext _localctx = new BtypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_btype);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__12) | (1L << T__13) | (1L << T__14) | (1L << T__15) | (1L << TYID))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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
		enterRule(_localctx, 18, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
			match(T__2);
			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__16) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << ID) | (1L << TYID))) != 0)) {
				{
				{
				setState(123);
				cmd();
				}
				}
				setState(128);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(129);
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
		enterRule(_localctx, 20, RULE_cmd);
		int _la;
		try {
			setState(196);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(131);
				block();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(132);
				match(T__16);
				setState(133);
				match(T__6);
				setState(134);
				exp();
				setState(135);
				match(T__7);
				setState(136);
				cmd();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(138);
				match(T__16);
				setState(139);
				match(T__6);
				setState(140);
				exp();
				setState(141);
				match(T__7);
				setState(142);
				cmd();
				setState(143);
				match(T__17);
				setState(144);
				cmd();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(146);
				match(T__18);
				setState(147);
				match(T__6);
				setState(148);
				itcond();
				setState(149);
				match(T__7);
				setState(150);
				cmd();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(152);
				match(T__19);
				setState(153);
				lvalue();
				setState(154);
				match(T__5);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(156);
				match(T__20);
				setState(157);
				exp();
				setState(158);
				match(T__5);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(160);
				match(T__21);
				setState(161);
				exp();
				setState(166);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(162);
					match(T__9);
					setState(163);
					exp();
					}
					}
					setState(168);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(169);
				match(T__5);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(171);
				lvalue();
				setState(172);
				match(T__22);
				setState(173);
				exp();
				setState(174);
				match(T__5);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(176);
				match(ID);
				setState(177);
				match(T__6);
				setState(179);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << INT) | (1L << FLOAT) | (1L << CHAR) | (1L << ID) | (1L << TYID))) != 0)) {
					{
					setState(178);
					exps();
					}
				}

				setState(181);
				match(T__7);
				setState(193);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__23) {
					{
					setState(182);
					match(T__23);
					setState(183);
					lvalue();
					setState(188);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__9) {
						{
						{
						setState(184);
						match(T__9);
						setState(185);
						lvalue();
						}
						}
						setState(190);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(191);
					match(T__24);
					}
				}

				setState(195);
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
		enterRule(_localctx, 22, RULE_itcond);
		try {
			setState(202);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(198);
				match(ID);
				setState(199);
				match(T__8);
				setState(200);
				exp();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(201);
				exp();
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
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public ExpLinhaContext expLinha() {
			return getRuleContext(ExpLinhaContext.class,0);
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
		ExpContext _localctx = new ExpContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_exp);
		int _la;
		try {
			setState(253);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(204);
				match(T__25);
				setState(205);
				exp();
				setState(206);
				expLinha();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(208);
				match(T__26);
				setState(209);
				exp();
				setState(210);
				expLinha();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(212);
				lvalue();
				setState(213);
				expLinha();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(215);
				match(T__6);
				setState(216);
				exp();
				setState(217);
				match(T__7);
				setState(218);
				expLinha();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(220);
				match(T__27);
				setState(221);
				type();
				setState(226);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__10) {
					{
					setState(222);
					match(T__10);
					setState(223);
					exp();
					setState(224);
					match(T__11);
					}
				}

				setState(228);
				expLinha();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(230);
				match(ID);
				setState(231);
				match(T__6);
				setState(233);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__25) | (1L << T__26) | (1L << T__27) | (1L << T__28) | (1L << T__29) | (1L << T__30) | (1L << INT) | (1L << FLOAT) | (1L << CHAR) | (1L << ID) | (1L << TYID))) != 0)) {
					{
					setState(232);
					exps();
					}
				}

				setState(235);
				match(T__7);
				setState(236);
				match(T__10);
				setState(237);
				exp();
				setState(238);
				match(T__11);
				setState(239);
				expLinha();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(241);
				match(T__28);
				setState(242);
				expLinha();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(243);
				match(T__29);
				setState(244);
				expLinha();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(245);
				match(T__30);
				setState(246);
				expLinha();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(247);
				match(INT);
				setState(248);
				expLinha();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(249);
				match(FLOAT);
				setState(250);
				expLinha();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(251);
				match(CHAR);
				setState(252);
				expLinha();
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

	public static class ExpLinhaContext extends ParserRuleContext {
		public OpContext op() {
			return getRuleContext(OpContext.class,0);
		}
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public ExpLinhaContext expLinha() {
			return getRuleContext(ExpLinhaContext.class,0);
		}
		public ExpLinhaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expLinha; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterExpLinha(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitExpLinha(this);
		}
	}

	public final ExpLinhaContext expLinha() throws RecognitionException {
		ExpLinhaContext _localctx = new ExpLinhaContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_expLinha);
		try {
			setState(260);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(255);
				op();
				setState(256);
				exp();
				setState(257);
				expLinha();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
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

	public static class OpContext extends ParserRuleContext {
		public OpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterOp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitOp(this);
		}
	}

	public final OpContext op() throws RecognitionException {
		OpContext _localctx = new OpContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__23) | (1L << T__26) | (1L << T__31) | (1L << T__32) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
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

	public static class LvalueContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public LvalueLinhaContext lvalueLinha() {
			return getRuleContext(LvalueLinhaContext.class,0);
		}
		public TerminalNode TYID() { return getToken(LangParser.TYID, 0); }
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
		LvalueContext _localctx = new LvalueContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_lvalue);
		try {
			setState(269);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(264);
				match(ID);
				setState(265);
				lvalueLinha();
				}
				break;
			case TYID:
				enterOuterAlt(_localctx, 2);
				{
				setState(266);
				match(TYID);
				setState(267);
				match(ID);
				setState(268);
				lvalueLinha();
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

	public static class LvalueLinhaContext extends ParserRuleContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public LvalueLinhaContext lvalueLinha() {
			return getRuleContext(LvalueLinhaContext.class,0);
		}
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public LvalueLinhaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lvalueLinha; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterLvalueLinha(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitLvalueLinha(this);
		}
	}

	public final LvalueLinhaContext lvalueLinha() throws RecognitionException {
		LvalueLinhaContext _localctx = new LvalueLinhaContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_lvalueLinha);
		try {
			setState(280);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__10:
				enterOuterAlt(_localctx, 1);
				{
				setState(271);
				match(T__10);
				setState(272);
				exp();
				setState(273);
				match(T__11);
				setState(274);
				lvalueLinha();
				}
				break;
			case T__38:
				enterOuterAlt(_localctx, 2);
				{
				setState(276);
				match(T__38);
				setState(277);
				match(ID);
				setState(278);
				lvalueLinha();
				}
				break;
			case T__5:
			case T__7:
			case T__9:
			case T__11:
			case T__22:
			case T__23:
			case T__24:
			case T__26:
			case T__31:
			case T__32:
			case T__33:
			case T__34:
			case T__35:
			case T__36:
			case T__37:
				enterOuterAlt(_localctx, 3);
				{
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

	public static class ExpsContext extends ParserRuleContext {
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
		enterRule(_localctx, 34, RULE_exps);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(282);
			exp();
			setState(287);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(283);
				match(T__9);
				setState(284);
				exp();
				}
				}
				setState(289);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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
		case 2:
			return data_sempred((DataContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean data_sempred(DataContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\61\u0125\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\3\2\7\2(\n\2\f\2\16\2+\13\2\3\3\3\3\5\3/\n\3\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\7\48\n\4\f\4\16\4;\13\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4C\n"+
		"\4\f\4\16\4F\13\4\3\4\7\4I\n\4\f\4\16\4L\13\4\3\5\3\5\3\5\3\5\3\5\3\6"+
		"\3\6\3\6\5\6V\n\6\3\6\3\6\3\6\3\6\3\6\7\6]\n\6\f\6\16\6`\13\6\5\6b\n\6"+
		"\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7m\n\7\f\7\16\7p\13\7\3\b\3\b\3"+
		"\b\3\t\3\t\3\t\3\t\5\ty\n\t\3\n\3\n\3\13\3\13\7\13\177\n\13\f\13\16\13"+
		"\u0082\13\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\7\f\u00a7\n\f\f\f\16\f\u00aa\13\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\5\f\u00b6\n\f\3\f\3\f\3\f\3\f\3\f\7\f\u00bd\n\f\f\f"+
		"\16\f\u00c0\13\f\3\f\3\f\5\f\u00c4\n\f\3\f\5\f\u00c7\n\f\3\r\3\r\3\r\3"+
		"\r\5\r\u00cd\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00e5\n\16"+
		"\3\16\3\16\3\16\3\16\3\16\5\16\u00ec\n\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u0100"+
		"\n\16\3\17\3\17\3\17\3\17\3\17\5\17\u0107\n\17\3\20\3\20\3\21\3\21\3\21"+
		"\3\21\3\21\5\21\u0110\n\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\5\22\u011b\n\22\3\23\3\23\3\23\7\23\u0120\n\23\f\23\16\23\u0123\13\23"+
		"\3\23\2\3\6\24\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$\2\4\4\2\17\22"+
		"..\5\2\32\32\35\35\"(\2\u013d\2)\3\2\2\2\4.\3\2\2\2\6\60\3\2\2\2\bM\3"+
		"\2\2\2\nR\3\2\2\2\fe\3\2\2\2\16q\3\2\2\2\20x\3\2\2\2\22z\3\2\2\2\24|\3"+
		"\2\2\2\26\u00c6\3\2\2\2\30\u00cc\3\2\2\2\32\u00ff\3\2\2\2\34\u0106\3\2"+
		"\2\2\36\u0108\3\2\2\2 \u010f\3\2\2\2\"\u011a\3\2\2\2$\u011c\3\2\2\2&("+
		"\5\4\3\2\'&\3\2\2\2(+\3\2\2\2)\'\3\2\2\2)*\3\2\2\2*\3\3\2\2\2+)\3\2\2"+
		"\2,/\5\6\4\2-/\5\n\6\2.,\3\2\2\2.-\3\2\2\2/\5\3\2\2\2\60\61\b\4\1\2\61"+
		"\62\7\3\2\2\62\63\7\4\2\2\63\64\7.\2\2\649\7\5\2\2\658\5\b\5\2\668\5\n"+
		"\6\2\67\65\3\2\2\2\67\66\3\2\2\28;\3\2\2\29\67\3\2\2\29:\3\2\2\2:<\3\2"+
		"\2\2;9\3\2\2\2<=\7\6\2\2=J\3\2\2\2>?\f\3\2\2?@\7.\2\2@D\7\5\2\2AC\5\b"+
		"\5\2BA\3\2\2\2CF\3\2\2\2DB\3\2\2\2DE\3\2\2\2EG\3\2\2\2FD\3\2\2\2GI\7\6"+
		"\2\2H>\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2K\7\3\2\2\2LJ\3\2\2\2MN\7"+
		"-\2\2NO\7\7\2\2OP\5\16\b\2PQ\7\b\2\2Q\t\3\2\2\2RS\7-\2\2SU\7\t\2\2TV\5"+
		"\f\7\2UT\3\2\2\2UV\3\2\2\2VW\3\2\2\2Wa\7\n\2\2XY\7\13\2\2Y^\5\16\b\2Z"+
		"[\7\f\2\2[]\5\16\b\2\\Z\3\2\2\2]`\3\2\2\2^\\\3\2\2\2^_\3\2\2\2_b\3\2\2"+
		"\2`^\3\2\2\2aX\3\2\2\2ab\3\2\2\2bc\3\2\2\2cd\5\26\f\2d\13\3\2\2\2ef\7"+
		"-\2\2fg\7\7\2\2gn\5\16\b\2hi\7\f\2\2ij\7-\2\2jk\7\7\2\2km\5\16\b\2lh\3"+
		"\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2o\r\3\2\2\2pn\3\2\2\2qr\5\22\n\2r"+
		"s\5\20\t\2s\17\3\2\2\2tu\7\r\2\2uv\7\16\2\2vy\5\20\t\2wy\3\2\2\2xt\3\2"+
		"\2\2xw\3\2\2\2y\21\3\2\2\2z{\t\2\2\2{\23\3\2\2\2|\u0080\7\5\2\2}\177\5"+
		"\26\f\2~}\3\2\2\2\177\u0082\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2"+
		"\u0081\u0083\3\2\2\2\u0082\u0080\3\2\2\2\u0083\u0084\7\6\2\2\u0084\25"+
		"\3\2\2\2\u0085\u00c7\5\24\13\2\u0086\u0087\7\23\2\2\u0087\u0088\7\t\2"+
		"\2\u0088\u0089\5\32\16\2\u0089\u008a\7\n\2\2\u008a\u008b\5\26\f\2\u008b"+
		"\u00c7\3\2\2\2\u008c\u008d\7\23\2\2\u008d\u008e\7\t\2\2\u008e\u008f\5"+
		"\32\16\2\u008f\u0090\7\n\2\2\u0090\u0091\5\26\f\2\u0091\u0092\7\24\2\2"+
		"\u0092\u0093\5\26\f\2\u0093\u00c7\3\2\2\2\u0094\u0095\7\25\2\2\u0095\u0096"+
		"\7\t\2\2\u0096\u0097\5\30\r\2\u0097\u0098\7\n\2\2\u0098\u0099\5\26\f\2"+
		"\u0099\u00c7\3\2\2\2\u009a\u009b\7\26\2\2\u009b\u009c\5 \21\2\u009c\u009d"+
		"\7\b\2\2\u009d\u00c7\3\2\2\2\u009e\u009f\7\27\2\2\u009f\u00a0\5\32\16"+
		"\2\u00a0\u00a1\7\b\2\2\u00a1\u00c7\3\2\2\2\u00a2\u00a3\7\30\2\2\u00a3"+
		"\u00a8\5\32\16\2\u00a4\u00a5\7\f\2\2\u00a5\u00a7\5\32\16\2\u00a6\u00a4"+
		"\3\2\2\2\u00a7\u00aa\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9"+
		"\u00ab\3\2\2\2\u00aa\u00a8\3\2\2\2\u00ab\u00ac\7\b\2\2\u00ac\u00c7\3\2"+
		"\2\2\u00ad\u00ae\5 \21\2\u00ae\u00af\7\31\2\2\u00af\u00b0\5\32\16\2\u00b0"+
		"\u00b1\7\b\2\2\u00b1\u00c7\3\2\2\2\u00b2\u00b3\7-\2\2\u00b3\u00b5\7\t"+
		"\2\2\u00b4\u00b6\5$\23\2\u00b5\u00b4\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6"+
		"\u00b7\3\2\2\2\u00b7\u00c3\7\n\2\2\u00b8\u00b9\7\32\2\2\u00b9\u00be\5"+
		" \21\2\u00ba\u00bb\7\f\2\2\u00bb\u00bd\5 \21\2\u00bc\u00ba\3\2\2\2\u00bd"+
		"\u00c0\3\2\2\2\u00be\u00bc\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c1\3\2"+
		"\2\2\u00c0\u00be\3\2\2\2\u00c1\u00c2\7\33\2\2\u00c2\u00c4\3\2\2\2\u00c3"+
		"\u00b8\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c7\7\b"+
		"\2\2\u00c6\u0085\3\2\2\2\u00c6\u0086\3\2\2\2\u00c6\u008c\3\2\2\2\u00c6"+
		"\u0094\3\2\2\2\u00c6\u009a\3\2\2\2\u00c6\u009e\3\2\2\2\u00c6\u00a2\3\2"+
		"\2\2\u00c6\u00ad\3\2\2\2\u00c6\u00b2\3\2\2\2\u00c7\27\3\2\2\2\u00c8\u00c9"+
		"\7-\2\2\u00c9\u00ca\7\13\2\2\u00ca\u00cd\5\32\16\2\u00cb\u00cd\5\32\16"+
		"\2\u00cc\u00c8\3\2\2\2\u00cc\u00cb\3\2\2\2\u00cd\31\3\2\2\2\u00ce\u00cf"+
		"\7\34\2\2\u00cf\u00d0\5\32\16\2\u00d0\u00d1\5\34\17\2\u00d1\u0100\3\2"+
		"\2\2\u00d2\u00d3\7\35\2\2\u00d3\u00d4\5\32\16\2\u00d4\u00d5\5\34\17\2"+
		"\u00d5\u0100\3\2\2\2\u00d6\u00d7\5 \21\2\u00d7\u00d8\5\34\17\2\u00d8\u0100"+
		"\3\2\2\2\u00d9\u00da\7\t\2\2\u00da\u00db\5\32\16\2\u00db\u00dc\7\n\2\2"+
		"\u00dc\u00dd\5\34\17\2\u00dd\u0100\3\2\2\2\u00de\u00df\7\36\2\2\u00df"+
		"\u00e4\5\16\b\2\u00e0\u00e1\7\r\2\2\u00e1\u00e2\5\32\16\2\u00e2\u00e3"+
		"\7\16\2\2\u00e3\u00e5\3\2\2\2\u00e4\u00e0\3\2\2\2\u00e4\u00e5\3\2\2\2"+
		"\u00e5\u00e6\3\2\2\2\u00e6\u00e7\5\34\17\2\u00e7\u0100\3\2\2\2\u00e8\u00e9"+
		"\7-\2\2\u00e9\u00eb\7\t\2\2\u00ea\u00ec\5$\23\2\u00eb\u00ea\3\2\2\2\u00eb"+
		"\u00ec\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ee\7\n\2\2\u00ee\u00ef\7\r"+
		"\2\2\u00ef\u00f0\5\32\16\2\u00f0\u00f1\7\16\2\2\u00f1\u00f2\5\34\17\2"+
		"\u00f2\u0100\3\2\2\2\u00f3\u00f4\7\37\2\2\u00f4\u0100\5\34\17\2\u00f5"+
		"\u00f6\7 \2\2\u00f6\u0100\5\34\17\2\u00f7\u00f8\7!\2\2\u00f8\u0100\5\34"+
		"\17\2\u00f9\u00fa\7*\2\2\u00fa\u0100\5\34\17\2\u00fb\u00fc\7+\2\2\u00fc"+
		"\u0100\5\34\17\2\u00fd\u00fe\7,\2\2\u00fe\u0100\5\34\17\2\u00ff\u00ce"+
		"\3\2\2\2\u00ff\u00d2\3\2\2\2\u00ff\u00d6\3\2\2\2\u00ff\u00d9\3\2\2\2\u00ff"+
		"\u00de\3\2\2\2\u00ff\u00e8\3\2\2\2\u00ff\u00f3\3\2\2\2\u00ff\u00f5\3\2"+
		"\2\2\u00ff\u00f7\3\2\2\2\u00ff\u00f9\3\2\2\2\u00ff\u00fb\3\2\2\2\u00ff"+
		"\u00fd\3\2\2\2\u0100\33\3\2\2\2\u0101\u0102\5\36\20\2\u0102\u0103\5\32"+
		"\16\2\u0103\u0104\5\34\17\2\u0104\u0107\3\2\2\2\u0105\u0107\3\2\2\2\u0106"+
		"\u0101\3\2\2\2\u0106\u0105\3\2\2\2\u0107\35\3\2\2\2\u0108\u0109\t\3\2"+
		"\2\u0109\37\3\2\2\2\u010a\u010b\7-\2\2\u010b\u0110\5\"\22\2\u010c\u010d"+
		"\7.\2\2\u010d\u010e\7-\2\2\u010e\u0110\5\"\22\2\u010f\u010a\3\2\2\2\u010f"+
		"\u010c\3\2\2\2\u0110!\3\2\2\2\u0111\u0112\7\r\2\2\u0112\u0113\5\32\16"+
		"\2\u0113\u0114\7\16\2\2\u0114\u0115\5\"\22\2\u0115\u011b\3\2\2\2\u0116"+
		"\u0117\7)\2\2\u0117\u0118\7-\2\2\u0118\u011b\5\"\22\2\u0119\u011b\3\2"+
		"\2\2\u011a\u0111\3\2\2\2\u011a\u0116\3\2\2\2\u011a\u0119\3\2\2\2\u011b"+
		"#\3\2\2\2\u011c\u0121\5\32\16\2\u011d\u011e\7\f\2\2\u011e\u0120\5\32\16"+
		"\2\u011f\u011d\3\2\2\2\u0120\u0123\3\2\2\2\u0121\u011f\3\2\2\2\u0121\u0122"+
		"\3\2\2\2\u0122%\3\2\2\2\u0123\u0121\3\2\2\2\33).\679DJU^anx\u0080\u00a8"+
		"\u00b5\u00be\u00c3\u00c6\u00cc\u00e4\u00eb\u00ff\u0106\u010f\u011a\u0121";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}