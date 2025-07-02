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
		RULE_fun = 5, RULE_params = 6, RULE_type = 7, RULE_block = 8, RULE_cmd = 9, 
		RULE_itcond = 10, RULE_exp = 11, RULE_lvalue = 12, RULE_exps = 13;
	private static String[] makeRuleNames() {
		return new String[] {
			"prog", "def", "data", "decl", "fun_abstract_data", "fun", "params", 
			"type", "block", "cmd", "itcond", "exp", "lvalue", "exps"
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
						((DataContext)_localctx).fun_abstract_data = fun_abstract_data();
						 members.add(((DataContext)_localctx).fun_abstract_data.ast); 
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
			setState(84);
			((Fun_abstract_dataContext)_localctx).fun_def = match(ID);
			setState(85);
			match(T__6);
			setState(87);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(86);
				((Fun_abstract_dataContext)_localctx).p = params();
				}
			}

			setState(89);
			match(T__7);
			setState(102);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(90);
				match(T__8);
				setState(91);
				((Fun_abstract_dataContext)_localctx).t1 = type(0);
				 members.add(((Fun_abstract_dataContext)_localctx).t1.ast); 
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(93);
					match(T__9);
					setState(94);
					((Fun_abstract_dataContext)_localctx).t = type(0);
					 members.add(((Fun_abstract_dataContext)_localctx).t.ast); 
					}
					}
					setState(101);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(104);
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
			setState(107);
			((FunContext)_localctx).fun_def = match(ID);
			setState(108);
			match(T__6);
			setState(110);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(109);
				((FunContext)_localctx).p = params();
				}
			}

			setState(112);
			match(T__7);
			setState(125);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(113);
				match(T__8);
				setState(114);
				((FunContext)_localctx).t1 = type(0);
				 members.add(((FunContext)_localctx).t1.ast); 
				setState(122);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(116);
					match(T__9);
					setState(117);
					((FunContext)_localctx).t = type(0);
					 members.add(((FunContext)_localctx).t.ast); 
					}
					}
					setState(124);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(127);
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
			setState(130);
			((ParamsContext)_localctx).id1 = match(ID);
			setState(131);
			match(T__4);
			setState(132);
			((ParamsContext)_localctx).t1 = type(0);
			listParam.add(new Param((((ParamsContext)_localctx).id1!=null?((ParamsContext)_localctx).id1.getText():null), ((ParamsContext)_localctx).t1.ast));
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(134);
				match(T__9);
				setState(135);
				((ParamsContext)_localctx).id2 = match(ID);
				setState(136);
				match(T__4);
				setState(137);
				((ParamsContext)_localctx).t2 = type(0);
				listParam.add(new Param((((ParamsContext)_localctx).id2!=null?((ParamsContext)_localctx).id2.getText():null), ((ParamsContext)_localctx).t2.ast));
				}
				}
				setState(144);
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
			setState(158);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__12:
				{
				setState(148);
				((TypeContext)_localctx).Int1 = match(T__12);
				((TypeContext)_localctx).ast =  new TypeInt((((TypeContext)_localctx).Int1!=null?((TypeContext)_localctx).Int1.getLine():0), (((TypeContext)_localctx).Int1!=null?((TypeContext)_localctx).Int1.getCharPositionInLine():0));
				}
				break;
			case T__13:
				{
				setState(150);
				((TypeContext)_localctx).Char1 = match(T__13);
				((TypeContext)_localctx).ast =  new TypeChar((((TypeContext)_localctx).Char1!=null?((TypeContext)_localctx).Char1.getLine():0), (((TypeContext)_localctx).Char1!=null?((TypeContext)_localctx).Char1.getCharPositionInLine():0));
				}
				break;
			case T__14:
				{
				setState(152);
				((TypeContext)_localctx).Bool1 = match(T__14);
				((TypeContext)_localctx).ast =  new TypeBool((((TypeContext)_localctx).Bool1!=null?((TypeContext)_localctx).Bool1.getLine():0), (((TypeContext)_localctx).Bool1!=null?((TypeContext)_localctx).Bool1.getCharPositionInLine():0));
				}
				break;
			case T__15:
				{
				setState(154);
				((TypeContext)_localctx).Float1 = match(T__15);
				((TypeContext)_localctx).ast =  new TypeFloat((((TypeContext)_localctx).Float1!=null?((TypeContext)_localctx).Float1.getLine():0), (((TypeContext)_localctx).Float1!=null?((TypeContext)_localctx).Float1.getCharPositionInLine():0));
				}
				break;
			case TYID:
				{
				setState(156);
				((TypeContext)_localctx).TYID = match(TYID);
				((TypeContext)_localctx).ast =  new TYID((((TypeContext)_localctx).TYID!=null?((TypeContext)_localctx).TYID.getLine():0), (((TypeContext)_localctx).TYID!=null?((TypeContext)_localctx).TYID.getCharPositionInLine():0), (((TypeContext)_localctx).TYID!=null?((TypeContext)_localctx).TYID.getText():null));
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(166);
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
					setState(160);
					if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
					setState(161);
					match(T__10);
					setState(162);
					match(T__11);
					((TypeContext)_localctx).ast =  new ArrayType(((TypeContext)_localctx).t1.ast.getLine(), ((TypeContext)_localctx).t1.ast.getCol());
					}
					} 
				}
				setState(168);
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
		 List<Cmd> listCmd = new ArrayList<>(); int line = -1; int col = -1; 
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			match(T__2);
			setState(175);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__16) | (1L << T__18) | (1L << T__19) | (1L << T__20) | (1L << T__21) | (1L << ID))) != 0)) {
				{
				{
				setState(170);
				((BlockContext)_localctx).c1 = cmd();

				                if (line == -1) { line = ((BlockContext)_localctx).c1.ast.getLine(); col = ((BlockContext)_localctx).c1.ast.getCol(); }
				                listCmd.add(((BlockContext)_localctx).c1.ast);
				            
				}
				}
				setState(177);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(178);
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
		enterRule(_localctx, 18, RULE_cmd);

		    	List<LValue> membersLValue = new ArrayList<>();
				List<Expr> membersReturn = new ArrayList<>();
		  	
		int _la;
		try {
			setState(262);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,20,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(181);
				((CmdContext)_localctx).block = block();
				 ((CmdContext)_localctx).ast =  ((CmdContext)_localctx).block.ast;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(184);
				((CmdContext)_localctx).ifCond = match(T__16);
				setState(185);
				match(T__6);
				setState(186);
				((CmdContext)_localctx).exp = exp(0);
				setState(187);
				match(T__7);
				setState(188);
				((CmdContext)_localctx).cmd = cmd();
				 ((CmdContext)_localctx).ast =  new CmdIf((((CmdContext)_localctx).ifCond!=null?((CmdContext)_localctx).ifCond.getLine():0), (((CmdContext)_localctx).ifCond!=null?((CmdContext)_localctx).ifCond.getCharPositionInLine():0), ((CmdContext)_localctx).exp.ast, ((CmdContext)_localctx).cmd.ast);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(191);
				((CmdContext)_localctx).ifCond = match(T__16);
				setState(192);
				match(T__6);
				setState(193);
				((CmdContext)_localctx).exp = exp(0);
				setState(194);
				match(T__7);
				setState(195);
				((CmdContext)_localctx).cmd1 = cmd();
				setState(196);
				match(T__17);
				setState(197);
				((CmdContext)_localctx).cmd2 = cmd();
				 ((CmdContext)_localctx).ast =  new CmdIf((((CmdContext)_localctx).ifCond!=null?((CmdContext)_localctx).ifCond.getLine():0), (((CmdContext)_localctx).ifCond!=null?((CmdContext)_localctx).ifCond.getCharPositionInLine():0), ((CmdContext)_localctx).exp.ast, ((CmdContext)_localctx).cmd1.ast, ((CmdContext)_localctx).cmd2.ast );
						
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(200);
				((CmdContext)_localctx).it = match(T__18);
				setState(201);
				match(T__6);
				setState(202);
				((CmdContext)_localctx).itcond = itcond();
				setState(203);
				match(T__7);
				setState(204);
				((CmdContext)_localctx).cmd = cmd();
				 ((CmdContext)_localctx).ast =  new CmdIterate((((CmdContext)_localctx).it!=null?((CmdContext)_localctx).it.getLine():0), (((CmdContext)_localctx).it!=null?((CmdContext)_localctx).it.getCharPositionInLine():0), ((CmdContext)_localctx).itcond.ast, ((CmdContext)_localctx).cmd.ast );
						
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(207);
				((CmdContext)_localctx).rd = match(T__19);
				setState(208);
				((CmdContext)_localctx).lvalue = lvalue(0);
				setState(209);
				match(T__5);
				 ((CmdContext)_localctx).ast =  new CmdRead((((CmdContext)_localctx).rd!=null?((CmdContext)_localctx).rd.getLine():0), (((CmdContext)_localctx).rd!=null?((CmdContext)_localctx).rd.getCharPositionInLine():0), ((CmdContext)_localctx).lvalue.ast); 
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(212);
				((CmdContext)_localctx).prt = match(T__20);
				setState(213);
				((CmdContext)_localctx).exp = exp(0);
				setState(214);
				match(T__5);
				 ((CmdContext)_localctx).ast =  new CmdPrint((((CmdContext)_localctx).prt!=null?((CmdContext)_localctx).prt.getLine():0), (((CmdContext)_localctx).prt!=null?((CmdContext)_localctx).prt.getCharPositionInLine():0), ((CmdContext)_localctx).exp.ast); 
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(217);
				((CmdContext)_localctx).rt = match(T__21);
				setState(218);
				((CmdContext)_localctx).exp = exp(0);
				 membersReturn.add(((CmdContext)_localctx).exp.ast) ;
				setState(226);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__9) {
					{
					{
					setState(220);
					match(T__9);
					setState(221);
					((CmdContext)_localctx).exp = exp(0);
					membersReturn.add(((CmdContext)_localctx).exp.ast);
					}
					}
					setState(228);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				 ((CmdContext)_localctx).ast =  new CmdReturn((((CmdContext)_localctx).rt!=null?((CmdContext)_localctx).rt.getLine():0), (((CmdContext)_localctx).rt!=null?((CmdContext)_localctx).rt.getCharPositionInLine():0), membersReturn);
				setState(230);
				match(T__5);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(232);
				((CmdContext)_localctx).lvalue = lvalue(0);
				setState(233);
				match(T__22);
				setState(234);
				((CmdContext)_localctx).exp = exp(0);
				setState(235);
				match(T__5);
				 ((CmdContext)_localctx).ast =  new CmdAssign(((CmdContext)_localctx).lvalue.ast.getLine(), ((CmdContext)_localctx).lvalue.ast.getCol(), ((CmdContext)_localctx).lvalue.ast, ((CmdContext)_localctx).exp.ast);
						
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(238);
				((CmdContext)_localctx).ID = match(ID);
				setState(239);
				match(T__6);
				setState(241);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__29) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << INT) | (1L << FLOAT) | (1L << CHAR) | (1L << ID))) != 0)) {
					{
					setState(240);
					((CmdContext)_localctx).exps = exps();
					}
				}

				setState(243);
				match(T__7);
				setState(259);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__23) {
					{
					setState(244);
					match(T__23);
					setState(245);
					((CmdContext)_localctx).l1 = lvalue(0);
					 membersLValue.add(((CmdContext)_localctx).l1.ast) ;
					setState(253);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==T__9) {
						{
						{
						setState(247);
						match(T__9);
						setState(248);
						((CmdContext)_localctx).l2 = lvalue(0);
						 membersLValue.add(((CmdContext)_localctx).l2.ast) ;
						}
						}
						setState(255);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(256);
					match(T__24);
					((CmdContext)_localctx).ast =  new CmdFuncCall((((CmdContext)_localctx).ID!=null?((CmdContext)_localctx).ID.getLine():0), (((CmdContext)_localctx).ID!=null?((CmdContext)_localctx).ID.getCharPositionInLine():0),(((CmdContext)_localctx).ID!=null?((CmdContext)_localctx).ID.getText():null), ((CmdContext)_localctx).exps != null ? ((CmdContext)_localctx).exps.ast : null, membersLValue );
								
					}
				}

				setState(261);
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
			setState(272);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(264);
				((ItcondContext)_localctx).ID = match(ID);
				setState(265);
				match(T__8);
				setState(266);
				((ItcondContext)_localctx).exp = exp(0);
				 ((ItcondContext)_localctx).ast =  new IdItCond((((ItcondContext)_localctx).ID!=null?((ItcondContext)_localctx).ID.getLine():0), (((ItcondContext)_localctx).ID!=null?((ItcondContext)_localctx).ID.getCharPositionInLine():0), (((ItcondContext)_localctx).ID!=null?((ItcondContext)_localctx).ID.getText():null), ((ItcondContext)_localctx).exp.ast);
						
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(269);
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
			setState(324);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(275);
				((ExpContext)_localctx).not = match(T__33);
				setState(276);
				((ExpContext)_localctx).exp = exp(12);
				 ((ExpContext)_localctx).ast =  new NotExpr((((ExpContext)_localctx).not!=null?((ExpContext)_localctx).not.getLine():0), (((ExpContext)_localctx).not!=null?((ExpContext)_localctx).not.getCharPositionInLine():0), ((ExpContext)_localctx).exp.ast);
				}
				break;
			case 2:
				{
				setState(279);
				((ExpContext)_localctx).minus = match(T__29);
				setState(280);
				((ExpContext)_localctx).exp = exp(11);
				 ((ExpContext)_localctx).ast =  new MinusExpr((((ExpContext)_localctx).minus!=null?((ExpContext)_localctx).minus.getLine():0), (((ExpContext)_localctx).minus!=null?((ExpContext)_localctx).minus.getCharPositionInLine():0), ((ExpContext)_localctx).exp.ast);
				}
				break;
			case 3:
				{
				setState(283);
				((ExpContext)_localctx).lvalue = lvalue(0);
				 ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).lvalue.ast;
						
				}
				break;
			case 4:
				{
				setState(286);
				match(T__6);
				setState(287);
				((ExpContext)_localctx).exp = exp(0);
				setState(288);
				match(T__7);
				 ((ExpContext)_localctx).ast =  ((ExpContext)_localctx).exp.ast; 
				}
				break;
			case 5:
				{
				setState(291);
				match(T__34);
				setState(292);
				((ExpContext)_localctx).type = type(0);
				setState(297);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
				case 1:
					{
					setState(293);
					match(T__10);
					setState(294);
					((ExpContext)_localctx).e = ((ExpContext)_localctx).exp = exp(0);
					setState(295);
					match(T__11);
					}
					break;
				}
				 ((ExpContext)_localctx).ast =  new VarExpr(((ExpContext)_localctx).type.ast.getLine(), ((ExpContext)_localctx).type.ast.getCol(), ((ExpContext)_localctx).type.ast, ((ExpContext)_localctx).e != null ? ((ExpContext)_localctx).e.ast : null);
				}
				break;
			case 6:
				{
				setState(301);
				((ExpContext)_localctx).ID = match(ID);
				setState(302);
				match(T__6);
				setState(304);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__6) | (1L << T__29) | (1L << T__33) | (1L << T__34) | (1L << T__35) | (1L << T__36) | (1L << T__37) | (1L << INT) | (1L << FLOAT) | (1L << CHAR) | (1L << ID))) != 0)) {
					{
					setState(303);
					((ExpContext)_localctx).exps = exps();
					}
				}

				setState(306);
				match(T__7);
				setState(307);
				match(T__10);
				setState(308);
				((ExpContext)_localctx).exp = exp(0);
				setState(309);
				match(T__11);
				 ((ExpContext)_localctx).ast =  new CallFunctionAccess((((ExpContext)_localctx).ID!=null?((ExpContext)_localctx).ID.getLine():0), (((ExpContext)_localctx).ID!=null?((ExpContext)_localctx).ID.getCharPositionInLine():0), (((ExpContext)_localctx).ID!=null?((ExpContext)_localctx).ID.getText():null), ((ExpContext)_localctx).exps !=null ? ((ExpContext)_localctx).exps.ast : null , ((ExpContext)_localctx).exp.ast);
						
				}
				break;
			case 7:
				{
				setState(312);
				((ExpContext)_localctx).t = match(T__35);
				 ((ExpContext)_localctx).ast =  new TrueValue((((ExpContext)_localctx).t!=null?((ExpContext)_localctx).t.getLine():0), (((ExpContext)_localctx).t!=null?((ExpContext)_localctx).t.getCharPositionInLine():0));
				}
				break;
			case 8:
				{
				setState(314);
				((ExpContext)_localctx).f = match(T__36);
				 ((ExpContext)_localctx).ast =  new FalseValue((((ExpContext)_localctx).f!=null?((ExpContext)_localctx).f.getLine():0), (((ExpContext)_localctx).f!=null?((ExpContext)_localctx).f.getCharPositionInLine():0));
				}
				break;
			case 9:
				{
				setState(316);
				((ExpContext)_localctx).n = match(T__37);
				 ((ExpContext)_localctx).ast =  new NullValue((((ExpContext)_localctx).n!=null?((ExpContext)_localctx).n.getLine():0), (((ExpContext)_localctx).n!=null?((ExpContext)_localctx).n.getCharPositionInLine():0));
				}
				break;
			case 10:
				{
				setState(318);
				((ExpContext)_localctx).INT = match(INT);
				 ((ExpContext)_localctx).ast =  new IntValue((((ExpContext)_localctx).INT!=null?((ExpContext)_localctx).INT.getLine():0), (((ExpContext)_localctx).INT!=null?((ExpContext)_localctx).INT.getCharPositionInLine():0), (((ExpContext)_localctx).INT!=null?((ExpContext)_localctx).INT.getText():null));
				}
				break;
			case 11:
				{
				setState(320);
				((ExpContext)_localctx).FLOAT = match(FLOAT);
				 ((ExpContext)_localctx).ast =  new FloatValue((((ExpContext)_localctx).FLOAT!=null?((ExpContext)_localctx).FLOAT.getLine():0), (((ExpContext)_localctx).FLOAT!=null?((ExpContext)_localctx).FLOAT.getCharPositionInLine():0), (((ExpContext)_localctx).FLOAT!=null?((ExpContext)_localctx).FLOAT.getText():null));
				}
				break;
			case 12:
				{
				setState(322);
				((ExpContext)_localctx).CHAR = match(CHAR);
				 ((ExpContext)_localctx).ast =  new CharValue((((ExpContext)_localctx).CHAR!=null?((ExpContext)_localctx).CHAR.getLine():0), (((ExpContext)_localctx).CHAR!=null?((ExpContext)_localctx).CHAR.getCharPositionInLine():0), (((ExpContext)_localctx).CHAR!=null?((ExpContext)_localctx).CHAR.getText():null));
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(373);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(371);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
					case 1:
						{
						_localctx = new ExpContext(_parentctx, _parentState);
						_localctx.exp1 = _prevctx;
						_localctx.exp1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_exp);
						setState(326);
						if (!(precpred(_ctx, 21))) throw new FailedPredicateException(this, "precpred(_ctx, 21)");
						setState(327);
						((ExpContext)_localctx).op = match(T__25);
						setState(328);
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
						setState(331);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(332);
						((ExpContext)_localctx).op = match(T__26);
						setState(333);
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
						setState(336);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(337);
						((ExpContext)_localctx).op = match(T__27);
						setState(338);
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
						setState(341);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(342);
						((ExpContext)_localctx).op = match(T__28);
						setState(343);
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
						setState(346);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(347);
						((ExpContext)_localctx).op = match(T__29);
						setState(348);
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
						setState(351);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(352);
						((ExpContext)_localctx).op = match(T__23);
						setState(353);
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
						setState(356);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(357);
						((ExpContext)_localctx).op = match(T__30);
						setState(358);
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
						setState(361);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(362);
						((ExpContext)_localctx).op = match(T__31);
						setState(363);
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
						setState(366);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(367);
						((ExpContext)_localctx).op = match(T__32);
						setState(368);
						((ExpContext)_localctx).exp2 = ((ExpContext)_localctx).exp = exp(14);
						 ((ExpContext)_localctx).ast =  new And(((ExpContext)_localctx).exp1.ast.getLine(), ((ExpContext)_localctx).exp1.ast.getCol(), ((ExpContext)_localctx).exp1.ast, ((ExpContext)_localctx).exp2.ast);
						}
						break;
					}
					} 
				}
				setState(375);
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
			setState(377);
			((LvalueContext)_localctx).ID = match(ID);
			 ((LvalueContext)_localctx).ast =  new ID((((LvalueContext)_localctx).ID!=null?((LvalueContext)_localctx).ID.getLine():0), (((LvalueContext)_localctx).ID!=null?((LvalueContext)_localctx).ID.getCharPositionInLine():0), (((LvalueContext)_localctx).ID!=null?((LvalueContext)_localctx).ID.getText():null));
			}
			_ctx.stop = _input.LT(-1);
			setState(392);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(390);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
					case 1:
						{
						_localctx = new LvalueContext(_parentctx, _parentState);
						_localctx.l1 = _prevctx;
						_localctx.l1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_lvalue);
						setState(380);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(381);
						match(T__10);
						setState(382);
						((LvalueContext)_localctx).exp = exp(0);
						setState(383);
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
						setState(386);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(387);
						match(T__38);
						setState(388);
						((LvalueContext)_localctx).ID = match(ID);
						((LvalueContext)_localctx).ast =  new IdLValue(((LvalueContext)_localctx).l1.ast.getLine(), ((LvalueContext)_localctx).l1.ast.getCol(), ((LvalueContext)_localctx).l1.ast, (((LvalueContext)_localctx).ID!=null?((LvalueContext)_localctx).ID.getText():null));
						}
						break;
					}
					} 
				}
				setState(394);
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
			setState(395);
			((ExpsContext)_localctx).e1 = exp(0);
			expsValues.add(((ExpsContext)_localctx).e1.ast);
			setState(403);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__9) {
				{
				{
				setState(397);
				match(T__9);
				setState(398);
				((ExpsContext)_localctx).e2 = exp(0);
				expsValues.add(((ExpsContext)_localctx).e2.ast);
				}
				}
				setState(405);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\61\u019b\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\7\2\"\n\2\f\2\16"+
		"\2%\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\5\3/\n\3\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\7\4;\n\4\f\4\16\4>\13\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\7\4H\n\4\f\4\16\4K\13\4\3\4\3\4\5\4O\n\4\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\6\3\6\3\6\5\6Z\n\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6d\n\6\f\6\16"+
		"\6g\13\6\5\6i\n\6\3\6\3\6\3\6\3\7\3\7\3\7\5\7q\n\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\7\7{\n\7\f\7\16\7~\13\7\5\7\u0080\n\7\3\7\3\7\3\7\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u008f\n\b\f\b\16\b\u0092\13\b\3\b"+
		"\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\5\t\u00a1\n\t\3\t\3\t"+
		"\3\t\3\t\7\t\u00a7\n\t\f\t\16\t\u00aa\13\t\3\n\3\n\3\n\3\n\7\n\u00b0\n"+
		"\n\f\n\16\n\u00b3\13\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00e3\n\13\f\13\16\13\u00e6"+
		"\13\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\5\13"+
		"\u00f4\n\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u00fe\n\13\f"+
		"\13\16\13\u0101\13\13\3\13\3\13\3\13\5\13\u0106\n\13\3\13\5\13\u0109\n"+
		"\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u0113\n\f\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\5\r\u012c\n\r\3\r\3\r\3\r\3\r\3\r\5\r\u0133\n\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u0147\n\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u0176\n\r\f\r\16\r\u0179\13\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\7\16\u0189\n\16\f\16\16\16\u018c\13\16\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\7\17\u0194\n\17\f\17\16\17\u0197\13\17\3\17\3\17\3\17\2\5\20\30\32\20"+
		"\2\4\6\b\n\f\16\20\22\24\26\30\32\34\2\2\2\u01c5\2#\3\2\2\2\4.\3\2\2\2"+
		"\6N\3\2\2\2\bP\3\2\2\2\nV\3\2\2\2\fm\3\2\2\2\16\u0084\3\2\2\2\20\u00a0"+
		"\3\2\2\2\22\u00ab\3\2\2\2\24\u0108\3\2\2\2\26\u0112\3\2\2\2\30\u0146\3"+
		"\2\2\2\32\u017a\3\2\2\2\34\u018d\3\2\2\2\36\37\5\4\3\2\37 \b\2\1\2 \""+
		"\3\2\2\2!\36\3\2\2\2\"%\3\2\2\2#!\3\2\2\2#$\3\2\2\2$&\3\2\2\2%#\3\2\2"+
		"\2&\'\b\2\1\2\'\3\3\2\2\2()\5\6\4\2)*\b\3\1\2*/\3\2\2\2+,\5\f\7\2,-\b"+
		"\3\1\2-/\3\2\2\2.(\3\2\2\2.+\3\2\2\2/\5\3\2\2\2\60\61\7\3\2\2\61\62\7"+
		"\4\2\2\62\63\7.\2\2\63<\7\5\2\2\64\65\5\b\5\2\65\66\b\4\1\2\66;\3\2\2"+
		"\2\678\5\n\6\289\b\4\1\29;\3\2\2\2:\64\3\2\2\2:\67\3\2\2\2;>\3\2\2\2<"+
		":\3\2\2\2<=\3\2\2\2=?\3\2\2\2><\3\2\2\2?@\7\6\2\2@O\b\4\1\2AB\7\4\2\2"+
		"BC\7.\2\2CI\7\5\2\2DE\5\b\5\2EF\b\4\1\2FH\3\2\2\2GD\3\2\2\2HK\3\2\2\2"+
		"IG\3\2\2\2IJ\3\2\2\2JL\3\2\2\2KI\3\2\2\2LM\7\6\2\2MO\b\4\1\2N\60\3\2\2"+
		"\2NA\3\2\2\2O\7\3\2\2\2PQ\7-\2\2QR\7\7\2\2RS\5\20\t\2ST\7\b\2\2TU\b\5"+
		"\1\2U\t\3\2\2\2VW\7-\2\2WY\7\t\2\2XZ\5\16\b\2YX\3\2\2\2YZ\3\2\2\2Z[\3"+
		"\2\2\2[h\7\n\2\2\\]\7\13\2\2]^\5\20\t\2^e\b\6\1\2_`\7\f\2\2`a\5\20\t\2"+
		"ab\b\6\1\2bd\3\2\2\2c_\3\2\2\2dg\3\2\2\2ec\3\2\2\2ef\3\2\2\2fi\3\2\2\2"+
		"ge\3\2\2\2h\\\3\2\2\2hi\3\2\2\2ij\3\2\2\2jk\5\24\13\2kl\b\6\1\2l\13\3"+
		"\2\2\2mn\7-\2\2np\7\t\2\2oq\5\16\b\2po\3\2\2\2pq\3\2\2\2qr\3\2\2\2r\177"+
		"\7\n\2\2st\7\13\2\2tu\5\20\t\2u|\b\7\1\2vw\7\f\2\2wx\5\20\t\2xy\b\7\1"+
		"\2y{\3\2\2\2zv\3\2\2\2{~\3\2\2\2|z\3\2\2\2|}\3\2\2\2}\u0080\3\2\2\2~|"+
		"\3\2\2\2\177s\3\2\2\2\177\u0080\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0082"+
		"\5\24\13\2\u0082\u0083\b\7\1\2\u0083\r\3\2\2\2\u0084\u0085\7-\2\2\u0085"+
		"\u0086\7\7\2\2\u0086\u0087\5\20\t\2\u0087\u0090\b\b\1\2\u0088\u0089\7"+
		"\f\2\2\u0089\u008a\7-\2\2\u008a\u008b\7\7\2\2\u008b\u008c\5\20\t\2\u008c"+
		"\u008d\b\b\1\2\u008d\u008f\3\2\2\2\u008e\u0088\3\2\2\2\u008f\u0092\3\2"+
		"\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u0093\3\2\2\2\u0092"+
		"\u0090\3\2\2\2\u0093\u0094\b\b\1\2\u0094\17\3\2\2\2\u0095\u0096\b\t\1"+
		"\2\u0096\u0097\7\17\2\2\u0097\u00a1\b\t\1\2\u0098\u0099\7\20\2\2\u0099"+
		"\u00a1\b\t\1\2\u009a\u009b\7\21\2\2\u009b\u00a1\b\t\1\2\u009c\u009d\7"+
		"\22\2\2\u009d\u00a1\b\t\1\2\u009e\u009f\7.\2\2\u009f\u00a1\b\t\1\2\u00a0"+
		"\u0095\3\2\2\2\u00a0\u0098\3\2\2\2\u00a0\u009a\3\2\2\2\u00a0\u009c\3\2"+
		"\2\2\u00a0\u009e\3\2\2\2\u00a1\u00a8\3\2\2\2\u00a2\u00a3\f\b\2\2\u00a3"+
		"\u00a4\7\r\2\2\u00a4\u00a5\7\16\2\2\u00a5\u00a7\b\t\1\2\u00a6\u00a2\3"+
		"\2\2\2\u00a7\u00aa\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9"+
		"\21\3\2\2\2\u00aa\u00a8\3\2\2\2\u00ab\u00b1\7\5\2\2\u00ac\u00ad\5\24\13"+
		"\2\u00ad\u00ae\b\n\1\2\u00ae\u00b0\3\2\2\2\u00af\u00ac\3\2\2\2\u00b0\u00b3"+
		"\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b2\3\2\2\2\u00b2\u00b4\3\2\2\2\u00b3"+
		"\u00b1\3\2\2\2\u00b4\u00b5\7\6\2\2\u00b5\u00b6\b\n\1\2\u00b6\23\3\2\2"+
		"\2\u00b7\u00b8\5\22\n\2\u00b8\u00b9\b\13\1\2\u00b9\u0109\3\2\2\2\u00ba"+
		"\u00bb\7\23\2\2\u00bb\u00bc\7\t\2\2\u00bc\u00bd\5\30\r\2\u00bd\u00be\7"+
		"\n\2\2\u00be\u00bf\5\24\13\2\u00bf\u00c0\b\13\1\2\u00c0\u0109\3\2\2\2"+
		"\u00c1\u00c2\7\23\2\2\u00c2\u00c3\7\t\2\2\u00c3\u00c4\5\30\r\2\u00c4\u00c5"+
		"\7\n\2\2\u00c5\u00c6\5\24\13\2\u00c6\u00c7\7\24\2\2\u00c7\u00c8\5\24\13"+
		"\2\u00c8\u00c9\b\13\1\2\u00c9\u0109\3\2\2\2\u00ca\u00cb\7\25\2\2\u00cb"+
		"\u00cc\7\t\2\2\u00cc\u00cd\5\26\f\2\u00cd\u00ce\7\n\2\2\u00ce\u00cf\5"+
		"\24\13\2\u00cf\u00d0\b\13\1\2\u00d0\u0109\3\2\2\2\u00d1\u00d2\7\26\2\2"+
		"\u00d2\u00d3\5\32\16\2\u00d3\u00d4\7\b\2\2\u00d4\u00d5\b\13\1\2\u00d5"+
		"\u0109\3\2\2\2\u00d6\u00d7\7\27\2\2\u00d7\u00d8\5\30\r\2\u00d8\u00d9\7"+
		"\b\2\2\u00d9\u00da\b\13\1\2\u00da\u0109\3\2\2\2\u00db\u00dc\7\30\2\2\u00dc"+
		"\u00dd\5\30\r\2\u00dd\u00e4\b\13\1\2\u00de\u00df\7\f\2\2\u00df\u00e0\5"+
		"\30\r\2\u00e0\u00e1\b\13\1\2\u00e1\u00e3\3\2\2\2\u00e2\u00de\3\2\2\2\u00e3"+
		"\u00e6\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e7\3\2"+
		"\2\2\u00e6\u00e4\3\2\2\2\u00e7\u00e8\b\13\1\2\u00e8\u00e9\7\b\2\2\u00e9"+
		"\u0109\3\2\2\2\u00ea\u00eb\5\32\16\2\u00eb\u00ec\7\31\2\2\u00ec\u00ed"+
		"\5\30\r\2\u00ed\u00ee\7\b\2\2\u00ee\u00ef\b\13\1\2\u00ef\u0109\3\2\2\2"+
		"\u00f0\u00f1\7-\2\2\u00f1\u00f3\7\t\2\2\u00f2\u00f4\5\34\17\2\u00f3\u00f2"+
		"\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u0105\7\n\2\2\u00f6"+
		"\u00f7\7\32\2\2\u00f7\u00f8\5\32\16\2\u00f8\u00ff\b\13\1\2\u00f9\u00fa"+
		"\7\f\2\2\u00fa\u00fb\5\32\16\2\u00fb\u00fc\b\13\1\2\u00fc\u00fe\3\2\2"+
		"\2\u00fd\u00f9\3\2\2\2\u00fe\u0101\3\2\2\2\u00ff\u00fd\3\2\2\2\u00ff\u0100"+
		"\3\2\2\2\u0100\u0102\3\2\2\2\u0101\u00ff\3\2\2\2\u0102\u0103\7\33\2\2"+
		"\u0103\u0104\b\13\1\2\u0104\u0106\3\2\2\2\u0105\u00f6\3\2\2\2\u0105\u0106"+
		"\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u0109\7\b\2\2\u0108\u00b7\3\2\2\2\u0108"+
		"\u00ba\3\2\2\2\u0108\u00c1\3\2\2\2\u0108\u00ca\3\2\2\2\u0108\u00d1\3\2"+
		"\2\2\u0108\u00d6\3\2\2\2\u0108\u00db\3\2\2\2\u0108\u00ea\3\2\2\2\u0108"+
		"\u00f0\3\2\2\2\u0109\25\3\2\2\2\u010a\u010b\7-\2\2\u010b\u010c\7\13\2"+
		"\2\u010c\u010d\5\30\r\2\u010d\u010e\b\f\1\2\u010e\u0113\3\2\2\2\u010f"+
		"\u0110\5\30\r\2\u0110\u0111\b\f\1\2\u0111\u0113\3\2\2\2\u0112\u010a\3"+
		"\2\2\2\u0112\u010f\3\2\2\2\u0113\27\3\2\2\2\u0114\u0115\b\r\1\2\u0115"+
		"\u0116\7$\2\2\u0116\u0117\5\30\r\16\u0117\u0118\b\r\1\2\u0118\u0147\3"+
		"\2\2\2\u0119\u011a\7 \2\2\u011a\u011b\5\30\r\r\u011b\u011c\b\r\1\2\u011c"+
		"\u0147\3\2\2\2\u011d\u011e\5\32\16\2\u011e\u011f\b\r\1\2\u011f\u0147\3"+
		"\2\2\2\u0120\u0121\7\t\2\2\u0121\u0122\5\30\r\2\u0122\u0123\7\n\2\2\u0123"+
		"\u0124\b\r\1\2\u0124\u0147\3\2\2\2\u0125\u0126\7%\2\2\u0126\u012b\5\20"+
		"\t\2\u0127\u0128\7\r\2\2\u0128\u0129\5\30\r\2\u0129\u012a\7\16\2\2\u012a"+
		"\u012c\3\2\2\2\u012b\u0127\3\2\2\2\u012b\u012c\3\2\2\2\u012c\u012d\3\2"+
		"\2\2\u012d\u012e\b\r\1\2\u012e\u0147\3\2\2\2\u012f\u0130\7-\2\2\u0130"+
		"\u0132\7\t\2\2\u0131\u0133\5\34\17\2\u0132\u0131\3\2\2\2\u0132\u0133\3"+
		"\2\2\2\u0133\u0134\3\2\2\2\u0134\u0135\7\n\2\2\u0135\u0136\7\r\2\2\u0136"+
		"\u0137\5\30\r\2\u0137\u0138\7\16\2\2\u0138\u0139\b\r\1\2\u0139\u0147\3"+
		"\2\2\2\u013a\u013b\7&\2\2\u013b\u0147\b\r\1\2\u013c\u013d\7\'\2\2\u013d"+
		"\u0147\b\r\1\2\u013e\u013f\7(\2\2\u013f\u0147\b\r\1\2\u0140\u0141\7*\2"+
		"\2\u0141\u0147\b\r\1\2\u0142\u0143\7+\2\2\u0143\u0147\b\r\1\2\u0144\u0145"+
		"\7,\2\2\u0145\u0147\b\r\1\2\u0146\u0114\3\2\2\2\u0146\u0119\3\2\2\2\u0146"+
		"\u011d\3\2\2\2\u0146\u0120\3\2\2\2\u0146\u0125\3\2\2\2\u0146\u012f\3\2"+
		"\2\2\u0146\u013a\3\2\2\2\u0146\u013c\3\2\2\2\u0146\u013e\3\2\2\2\u0146"+
		"\u0140\3\2\2\2\u0146\u0142\3\2\2\2\u0146\u0144\3\2\2\2\u0147\u0177\3\2"+
		"\2\2\u0148\u0149\f\27\2\2\u0149\u014a\7\34\2\2\u014a\u014b\5\30\r\30\u014b"+
		"\u014c\b\r\1\2\u014c\u0176\3\2\2\2\u014d\u014e\f\26\2\2\u014e\u014f\7"+
		"\35\2\2\u014f\u0150\5\30\r\27\u0150\u0151\b\r\1\2\u0151\u0176\3\2\2\2"+
		"\u0152\u0153\f\25\2\2\u0153\u0154\7\36\2\2\u0154\u0155\5\30\r\26\u0155"+
		"\u0156\b\r\1\2\u0156\u0176\3\2\2\2\u0157\u0158\f\24\2\2\u0158\u0159\7"+
		"\37\2\2\u0159\u015a\5\30\r\25\u015a\u015b\b\r\1\2\u015b\u0176\3\2\2\2"+
		"\u015c\u015d\f\23\2\2\u015d\u015e\7 \2\2\u015e\u015f\5\30\r\24\u015f\u0160"+
		"\b\r\1\2\u0160\u0176\3\2\2\2\u0161\u0162\f\22\2\2\u0162\u0163\7\32\2\2"+
		"\u0163\u0164\5\30\r\23\u0164\u0165\b\r\1\2\u0165\u0176\3\2\2\2\u0166\u0167"+
		"\f\21\2\2\u0167\u0168\7!\2\2\u0168\u0169\5\30\r\22\u0169\u016a\b\r\1\2"+
		"\u016a\u0176\3\2\2\2\u016b\u016c\f\20\2\2\u016c\u016d\7\"\2\2\u016d\u016e"+
		"\5\30\r\21\u016e\u016f\b\r\1\2\u016f\u0176\3\2\2\2\u0170\u0171\f\17\2"+
		"\2\u0171\u0172\7#\2\2\u0172\u0173\5\30\r\20\u0173\u0174\b\r\1\2\u0174"+
		"\u0176\3\2\2\2\u0175\u0148\3\2\2\2\u0175\u014d\3\2\2\2\u0175\u0152\3\2"+
		"\2\2\u0175\u0157\3\2\2\2\u0175\u015c\3\2\2\2\u0175\u0161\3\2\2\2\u0175"+
		"\u0166\3\2\2\2\u0175\u016b\3\2\2\2\u0175\u0170\3\2\2\2\u0176\u0179\3\2"+
		"\2\2\u0177\u0175\3\2\2\2\u0177\u0178\3\2\2\2\u0178\31\3\2\2\2\u0179\u0177"+
		"\3\2\2\2\u017a\u017b\b\16\1\2\u017b\u017c\7-\2\2\u017c\u017d\b\16\1\2"+
		"\u017d\u018a\3\2\2\2\u017e\u017f\f\4\2\2\u017f\u0180\7\r\2\2\u0180\u0181"+
		"\5\30\r\2\u0181\u0182\7\16\2\2\u0182\u0183\b\16\1\2\u0183\u0189\3\2\2"+
		"\2\u0184\u0185\f\3\2\2\u0185\u0186\7)\2\2\u0186\u0187\7-\2\2\u0187\u0189"+
		"\b\16\1\2\u0188\u017e\3\2\2\2\u0188\u0184\3\2\2\2\u0189\u018c\3\2\2\2"+
		"\u018a\u0188\3\2\2\2\u018a\u018b\3\2\2\2\u018b\33\3\2\2\2\u018c\u018a"+
		"\3\2\2\2\u018d\u018e\5\30\r\2\u018e\u0195\b\17\1\2\u018f\u0190\7\f\2\2"+
		"\u0190\u0191\5\30\r\2\u0191\u0192\b\17\1\2\u0192\u0194\3\2\2\2\u0193\u018f"+
		"\3\2\2\2\u0194\u0197\3\2\2\2\u0195\u0193\3\2\2\2\u0195\u0196\3\2\2\2\u0196"+
		"\u0198\3\2\2\2\u0197\u0195\3\2\2\2\u0198\u0199\b\17\1\2\u0199\35\3\2\2"+
		"\2 #.:<INYehp|\177\u0090\u00a0\u00a8\u00b1\u00e4\u00f3\u00ff\u0105\u0108"+
		"\u0112\u012b\u0132\u0146\u0175\u0177\u0188\u018a\u0195";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}