/*
 * JSP generated by Resin-3.1.8 (built Mon, 17 Nov 2008 12:15:21 PST)
 */

package _jsp._web_22dinf._content._report;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;
import com.item.utils.PropertyUtils;

public class _report_0getchannelzone__jsp extends com.caucho.jsp.JavaPage
{
  private static final java.util.HashMap<String,java.lang.reflect.Method> _jsp_functionMap = new java.util.HashMap<String,java.lang.reflect.Method>();
  private boolean _caucho_isDead;
  
  public void
  _jspService(javax.servlet.http.HttpServletRequest request,
              javax.servlet.http.HttpServletResponse response)
    throws java.io.IOException, javax.servlet.ServletException
  {
    javax.servlet.http.HttpSession session = request.getSession(true);
    com.caucho.server.webapp.WebApp _jsp_application = _caucho_getApplication();
    javax.servlet.ServletContext application = _jsp_application;
    com.caucho.jsp.PageContextImpl pageContext = _jsp_application.getJspApplicationContext().allocatePageContext(this, _jsp_application, request, response, null, session, 8192, true, false);
    javax.servlet.jsp.PageContext _jsp_parentContext = pageContext;
    javax.servlet.jsp.JspWriter out = pageContext.getOut();
    final javax.el.ELContext _jsp_env = pageContext.getELContext();
    javax.servlet.ServletConfig config = getServletConfig();
    javax.servlet.Servlet page = this;
    response.setContentType("text/html;charset=UTF-8");
    request.setCharacterEncoding("UTF-8");
    org.apache.struts2.views.jsp.TextTag _jsp_TextTag_0 = null;
    com.caucho.jsp.IntegerLoopSupportTag _jsp_loop_0 = null;
    com.caucho.jsp.IteratorLoopSupportTag _jsp_loop_1 = null;
    try {
      out.write(_jsp_string0, 0, _jsp_string0.length);
      _caucho_expr_0.print(out, _jsp_env, false);
      out.write(_jsp_string1, 0, _jsp_string1.length);
      if (_jsp_TextTag_0 == null) {
        _jsp_TextTag_0 = new org.apache.struts2.views.jsp.TextTag();
        _jsp_TextTag_0.setPageContext(pageContext);
        _jsp_TextTag_0.setParent((javax.servlet.jsp.tagext.Tag) null);
        _jsp_TextTag_0.setName("sys.project.name");
      }

      int _jspEval4 = _jsp_TextTag_0.doStartTag();
      _jsp_TextTag_0.doEndTag();
      out.write(_jsp_string2, 0, _jsp_string2.length);
      _caucho_expr_0.print(out, _jsp_env, false);
      out.write(_jsp_string3, 0, _jsp_string3.length);
      _caucho_expr_0.print(out, _jsp_env, false);
      out.write(_jsp_string4, 0, _jsp_string4.length);
      _caucho_expr_0.print(out, _jsp_env, false);
      out.write(_jsp_string5, 0, _jsp_string5.length);
      _caucho_expr_0.print(out, _jsp_env, false);
      out.write(_jsp_string6, 0, _jsp_string6.length);
      _caucho_expr_0.print(out, _jsp_env, false);
      out.write(_jsp_string7, 0, _jsp_string7.length);
      _caucho_expr_0.print(out, _jsp_env, false);
      out.write(_jsp_string8, 0, _jsp_string8.length);
      _caucho_expr_0.print(out, _jsp_env, false);
      out.write(_jsp_string9, 0, _jsp_string9.length);
      _caucho_expr_0.print(out, _jsp_env, false);
      out.write(_jsp_string10, 0, _jsp_string10.length);
      _caucho_expr_0.print(out, _jsp_env, false);
      out.write(_jsp_string11, 0, _jsp_string11.length);
      _caucho_expr_0.print(out, _jsp_env, false);
      out.write(_jsp_string12, 0, _jsp_string12.length);
      _caucho_expr_0.print(out, _jsp_env, false);
      out.write(_jsp_string13, 0, _jsp_string13.length);
      _caucho_expr_0.print(out, _jsp_env, false);
      out.write(_jsp_string14, 0, _jsp_string14.length);
      _caucho_expr_0.print(out, _jsp_env, false);
      out.write(_jsp_string15, 0, _jsp_string15.length);
      _caucho_expr_0.print(out, _jsp_env, false);
      out.write(_jsp_string16, 0, _jsp_string16.length);
      _caucho_expr_0.print(out, _jsp_env, false);
      out.write(_jsp_string17, 0, _jsp_string17.length);
      _caucho_expr_0.print(out, _jsp_env, false);
      out.write(_jsp_string18, 0, _jsp_string18.length);
      _caucho_expr_0.print(out, _jsp_env, false);
      out.write(_jsp_string19, 0, _jsp_string19.length);
      _caucho_expr_1.print(out, _jsp_env, false);
      out.write(_jsp_string20, 0, _jsp_string20.length);
      if (_jsp_loop_0 == null)
        _jsp_loop_0 = new com.caucho.jsp.IntegerLoopSupportTag();
      int _jsp_begin_6 = 0;
      int _jsp_end_6 = (int) _caucho_expr_2.evalLong(_jsp_env);
      _jsp_loop_0.init(_jsp_begin_6, _jsp_end_6, 1);
      Object _jsp_status_6 = pageContext.putAttribute("index", _jsp_loop_0);
      for (int _jsp_i_6 = _jsp_begin_6; _jsp_i_6 <= _jsp_end_6; _jsp_i_6 += 1) {
        _jsp_loop_0.setCurrent(_jsp_i_6);
        out.write(_jsp_string21, 0, _jsp_string21.length);
        if (_jsp_loop_1 == null)
          _jsp_loop_1 = new com.caucho.jsp.IteratorLoopSupportTag();
        java.lang.Object _jsp_items_7 = _caucho_expr_3.evalObject(_jsp_env);
        java.util.Iterator _jsp_iter_7 = com.caucho.jstl.rt.CoreForEachTag.getIterator(_jsp_items_7);
        int _jsp_begin_7 = (int) _caucho_expr_4.evalLong(_jsp_env);
        for (int _jsp_int_7 = _jsp_begin_7;_jsp_int_7 > 0; _jsp_int_7--)
          if (_jsp_iter_7.hasNext()) _jsp_iter_7.next();
        int _jsp_end_7 = (int) _caucho_expr_5.evalLong(_jsp_env);
        _jsp_loop_1.init(_jsp_begin_7, _jsp_end_7, 1);
        Object _jsp_oldVar_7 = pageContext.getAttribute("item");
        for (int _jsp_int_7 = _jsp_begin_7; _jsp_int_7 <= _jsp_end_7 && _jsp_iter_7.hasNext(); _jsp_int_7 += 1) {
          Object _jsp_i_7 = _jsp_iter_7.next();
          pageContext.setAttribute("item", _jsp_i_7);
          _jsp_loop_1.setCurrent(_jsp_i_7, _jsp_iter_7.hasNext());
          out.write(_jsp_string22, 0, _jsp_string22.length);
          if (_caucho_expr_6.evalBoolean(_jsp_env)) {
            out.write(_jsp_string22, 0, _jsp_string22.length);
            if (_caucho_expr_7.evalBoolean(_jsp_env)) {
              out.write(_jsp_string23, 0, _jsp_string23.length);
              _caucho_expr_8.print(out, _jsp_env, false);
              out.write(_jsp_string24, 0, _jsp_string24.length);
              _caucho_expr_9.print(out, _jsp_env, false);
              out.write(_jsp_string25, 0, _jsp_string25.length);
            }
            out.write(_jsp_string22, 0, _jsp_string22.length);
            if (_caucho_expr_10.evalBoolean(_jsp_env)) {
              out.write(_jsp_string26, 0, _jsp_string26.length);
              _caucho_expr_8.print(out, _jsp_env, false);
              out.write(_jsp_string24, 0, _jsp_string24.length);
              _caucho_expr_8.print(out, _jsp_env, false);
              out.write(_jsp_string25, 0, _jsp_string25.length);
            }
            out.write(_jsp_string22, 0, _jsp_string22.length);
          }
          out.write(_jsp_string22, 0, _jsp_string22.length);
        }
        pageContext.pageSetOrRemove("item", _jsp_oldVar_7);
        out.write(_jsp_string27, 0, _jsp_string27.length);
      }
      pageContext.removeAttribute("index");
      out.write(_jsp_string28, 0, _jsp_string28.length);
      if (_jsp_loop_0 == null)
        _jsp_loop_0 = new com.caucho.jsp.IntegerLoopSupportTag();
      int _jsp_begin_8 = 0;
      int _jsp_end_8 = (int) _caucho_expr_11.evalLong(_jsp_env);
      _jsp_loop_0.init(_jsp_begin_8, _jsp_end_8, 1);
      Object _jsp_status_8 = pageContext.putAttribute("index", _jsp_loop_0);
      for (int _jsp_i_8 = _jsp_begin_8; _jsp_i_8 <= _jsp_end_8; _jsp_i_8 += 1) {
        _jsp_loop_0.setCurrent(_jsp_i_8);
        out.write(_jsp_string21, 0, _jsp_string21.length);
        if (_jsp_loop_1 == null)
          _jsp_loop_1 = new com.caucho.jsp.IteratorLoopSupportTag();
        java.lang.Object _jsp_items_9 = _caucho_expr_12.evalObject(_jsp_env);
        java.util.Iterator _jsp_iter_9 = com.caucho.jstl.rt.CoreForEachTag.getIterator(_jsp_items_9);
        int _jsp_begin_9 = (int) _caucho_expr_4.evalLong(_jsp_env);
        for (int _jsp_int_9 = _jsp_begin_9;_jsp_int_9 > 0; _jsp_int_9--)
          if (_jsp_iter_9.hasNext()) _jsp_iter_9.next();
        int _jsp_end_9 = (int) _caucho_expr_5.evalLong(_jsp_env);
        _jsp_loop_1.init(_jsp_begin_9, _jsp_end_9, 1);
        Object _jsp_oldVar_9 = pageContext.getAttribute("item");
        for (int _jsp_int_9 = _jsp_begin_9; _jsp_int_9 <= _jsp_end_9 && _jsp_iter_9.hasNext(); _jsp_int_9 += 1) {
          Object _jsp_i_9 = _jsp_iter_9.next();
          pageContext.setAttribute("item", _jsp_i_9);
          _jsp_loop_1.setCurrent(_jsp_i_9, _jsp_iter_9.hasNext());
          out.write(_jsp_string22, 0, _jsp_string22.length);
          if (_caucho_expr_13.evalBoolean(_jsp_env)) {
            out.write(_jsp_string22, 0, _jsp_string22.length);
            if (_caucho_expr_14.evalBoolean(_jsp_env)) {
              out.write(_jsp_string29, 0, _jsp_string29.length);
              _caucho_expr_15.print(out, _jsp_env, false);
              out.write(_jsp_string24, 0, _jsp_string24.length);
              _caucho_expr_16.print(out, _jsp_env, false);
              out.write(_jsp_string30, 0, _jsp_string30.length);
            }
            out.write(_jsp_string31, 0, _jsp_string31.length);
            if (_caucho_expr_17.evalBoolean(_jsp_env)) {
              out.write(_jsp_string29, 0, _jsp_string29.length);
              _caucho_expr_15.print(out, _jsp_env, false);
              out.write(_jsp_string24, 0, _jsp_string24.length);
              _caucho_expr_15.print(out, _jsp_env, false);
              out.write(_jsp_string30, 0, _jsp_string30.length);
            }
            out.write(_jsp_string22, 0, _jsp_string22.length);
          }
          out.write(_jsp_string22, 0, _jsp_string22.length);
        }
        pageContext.pageSetOrRemove("item", _jsp_oldVar_9);
        out.write(_jsp_string27, 0, _jsp_string27.length);
      }
      pageContext.removeAttribute("index");
      out.write(_jsp_string32, 0, _jsp_string32.length);
      _caucho_expr_18.print(out, _jsp_env, false);
      out.write(_jsp_string33, 0, _jsp_string33.length);
      _caucho_expr_18.print(out, _jsp_env, false);
      out.write(_jsp_string34, 0, _jsp_string34.length);
      _caucho_expr_0.print(out, _jsp_env, false);
      out.write(_jsp_string35, 0, _jsp_string35.length);
      _caucho_expr_0.print(out, _jsp_env, false);
      out.write(_jsp_string36, 0, _jsp_string36.length);
      _caucho_expr_19.print(out, _jsp_env, false);
      out.write(_jsp_string37, 0, _jsp_string37.length);
    } catch (java.lang.Throwable _jsp_e) {
      pageContext.handlePageException(_jsp_e);
    } finally {
      if (_jsp_TextTag_0 != null)
        _jsp_TextTag_0.release();
      _jsp_application.getJspApplicationContext().freePageContext(pageContext);
    }
  }

  private java.util.ArrayList _caucho_depends = new java.util.ArrayList();

  public java.util.ArrayList _caucho_getDependList()
  {
    return _caucho_depends;
  }

  public void _caucho_addDepend(com.caucho.vfs.PersistentDependency depend)
  {
    super._caucho_addDepend(depend);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
  }

  public boolean _caucho_isModified()
  {
    if (_caucho_isDead)
      return true;
    if (com.caucho.server.util.CauchoSystem.getVersionId() != 1886798272571451039L)
      return true;
    for (int i = _caucho_depends.size() - 1; i >= 0; i--) {
      com.caucho.vfs.Dependency depend;
      depend = (com.caucho.vfs.Dependency) _caucho_depends.get(i);
      if (depend.isModified())
        return true;
    }
    return false;
  }

  public long _caucho_lastModified()
  {
    return 0;
  }

  public java.util.HashMap<String,java.lang.reflect.Method> _caucho_getFunctionMap()
  {
    return _jsp_functionMap;
  }

  public void init(ServletConfig config)
    throws ServletException
  {
    com.caucho.server.webapp.WebApp webApp
      = (com.caucho.server.webapp.WebApp) config.getServletContext();
    super.init(config);
    com.caucho.jsp.TaglibManager manager = webApp.getJspApplicationContext().getTaglibManager();
    manager.addTaglibFunctions(_jsp_functionMap, "s", "/struts-tags");
    manager.addTaglibFunctions(_jsp_functionMap, "c", "http://java.sun.com/jsp/jstl/core");
    manager.addTaglibFunctions(_jsp_functionMap, "fn", "http://java.sun.com/jsp/jstl/functions");
    manager.addTaglibFunctions(_jsp_functionMap, "fmt", "http://java.sun.com/jsp/jstl/fmt");
    manager.addTaglibFunctions(_jsp_functionMap, "mt", "/WEB-INF/classes/component/Tag.tld");
    com.caucho.jsp.PageContextImpl pageContext = new com.caucho.jsp.PageContextImpl(webApp, this);
    _caucho_expr_0 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${ctx}");
    _caucho_expr_1 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${appId }");
    _caucho_expr_2 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${fn:length(platformApps)/4 }");
    _caucho_expr_3 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${platformApps }");
    _caucho_expr_4 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${index.index * 4 }");
    _caucho_expr_5 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${(index.index+1)*4-1 }");
    _caucho_expr_6 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${not empty item.platformId}");
    _caucho_expr_7 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${not empty item.platformName }");
    _caucho_expr_8 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${item.platformId }");
    _caucho_expr_9 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${item.platformName }");
    _caucho_expr_10 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${empty item.platformName }");
    _caucho_expr_11 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${fn:length(gamezones)/4 }");
    _caucho_expr_12 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${gamezones }");
    _caucho_expr_13 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${not empty item.zoneId}");
    _caucho_expr_14 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${not empty item.zoneName}");
    _caucho_expr_15 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${item.zoneId }");
    _caucho_expr_16 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${item.zoneName }");
    _caucho_expr_17 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${empty item.zoneName}");
    _caucho_expr_18 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${isCompare}");
    _caucho_expr_19 = com.caucho.jsp.JspUtil.createExpr(pageContext.getELContext(), "${appId}");
  }

  public void destroy()
  {
      _caucho_isDead = true;
      super.destroy();
  }

  public void init(com.caucho.vfs.Path appDir)
    throws javax.servlet.ServletException
  {
    com.caucho.vfs.Path resinHome = com.caucho.server.util.CauchoSystem.getResinHome();
    com.caucho.vfs.MergePath mergePath = new com.caucho.vfs.MergePath();
    mergePath.addMergePath(appDir);
    mergePath.addMergePath(resinHome);
    com.caucho.loader.DynamicClassLoader loader;
    loader = (com.caucho.loader.DynamicClassLoader) getClass().getClassLoader();
    String resourcePath = loader.getResourcePathSpecificFirst();
    mergePath.addClassPath(resourcePath);
    com.caucho.vfs.Depend depend;
    depend = new com.caucho.vfs.Depend(appDir.lookup("WEB-INF/content/report/report_getChannelZone.jsp"), -2055086716398203393L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
    depend = new com.caucho.vfs.Depend(appDir.lookup("common/taglibs.jsp"), 8529732156098823754L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
    depend = new com.caucho.vfs.Depend(appDir.lookup("common/jquerylibs.jsp"), 3717867231385631855L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
    depend = new com.caucho.vfs.Depend(appDir.lookup("common/meta.jsp"), 6399001386991385174L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
    depend = new com.caucho.vfs.Depend(mergePath.lookup("jar:file:/D:/workspace/release-sdk-manage/WebRoot/WEB-INF/lib/struts2-core-2.3.15.1.jar!/META-INF/struts-tags.tld"), 5685907402043086835L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, new com.caucho.make.ClassDependency(org.apache.struts2.views.jsp.TextTag.class, -2428550431469489681L));
  }

  static {
    try {
    } catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }
  private static com.caucho.el.Expr _caucho_expr_0;
  private static com.caucho.el.Expr _caucho_expr_1;
  private static com.caucho.el.Expr _caucho_expr_2;
  private static com.caucho.el.Expr _caucho_expr_3;
  private static com.caucho.el.Expr _caucho_expr_4;
  private static com.caucho.el.Expr _caucho_expr_5;
  private static com.caucho.el.Expr _caucho_expr_6;
  private static com.caucho.el.Expr _caucho_expr_7;
  private static com.caucho.el.Expr _caucho_expr_8;
  private static com.caucho.el.Expr _caucho_expr_9;
  private static com.caucho.el.Expr _caucho_expr_10;
  private static com.caucho.el.Expr _caucho_expr_11;
  private static com.caucho.el.Expr _caucho_expr_12;
  private static com.caucho.el.Expr _caucho_expr_13;
  private static com.caucho.el.Expr _caucho_expr_14;
  private static com.caucho.el.Expr _caucho_expr_15;
  private static com.caucho.el.Expr _caucho_expr_16;
  private static com.caucho.el.Expr _caucho_expr_17;
  private static com.caucho.el.Expr _caucho_expr_18;
  private static com.caucho.el.Expr _caucho_expr_19;

  private final static char []_jsp_string29;
  private final static char []_jsp_string30;
  private final static char []_jsp_string23;
  private final static char []_jsp_string9;
  private final static char []_jsp_string27;
  private final static char []_jsp_string26;
  private final static char []_jsp_string4;
  private final static char []_jsp_string34;
  private final static char []_jsp_string24;
  private final static char []_jsp_string15;
  private final static char []_jsp_string1;
  private final static char []_jsp_string36;
  private final static char []_jsp_string5;
  private final static char []_jsp_string22;
  private final static char []_jsp_string3;
  private final static char []_jsp_string6;
  private final static char []_jsp_string32;
  private final static char []_jsp_string0;
  private final static char []_jsp_string31;
  private final static char []_jsp_string14;
  private final static char []_jsp_string10;
  private final static char []_jsp_string21;
  private final static char []_jsp_string12;
  private final static char []_jsp_string19;
  private final static char []_jsp_string2;
  private final static char []_jsp_string16;
  private final static char []_jsp_string35;
  private final static char []_jsp_string20;
  private final static char []_jsp_string17;
  private final static char []_jsp_string37;
  private final static char []_jsp_string13;
  private final static char []_jsp_string8;
  private final static char []_jsp_string28;
  private final static char []_jsp_string7;
  private final static char []_jsp_string18;
  private final static char []_jsp_string33;
  private final static char []_jsp_string11;
  private final static char []_jsp_string25;
  static {
    _jsp_string29 = "\r\n                                 <td><input class=\"zoneId\" type=\"checkbox\" value=\"".toCharArray();
    _jsp_string30 = "&nbsp;&nbsp;&nbsp;</td>\r\n                              ".toCharArray();
    _jsp_string23 = "\r\n								<td><input class=\"platformId\" type=\"checkbox\" value=\"".toCharArray();
    _jsp_string9 = "/js/plugins/messenger-master/css/messenger.css\" />\r\n<link rel=\"stylesheet\" type=\"text/css\" href=\"".toCharArray();
    _jsp_string27 = "\r\n						</tr>\r\n					".toCharArray();
    _jsp_string26 = "\r\n							<td><input class=\"platformId\" type=\"checkbox\" value=\"".toCharArray();
    _jsp_string4 = "/js/font-awesome/css/font-awesome.css\" rel=\"stylesheet\" type=\"text/css\">\r\n<!-- \u81ea\u5b9a\u4e49bootstrap\u4e3b\u9898\u6837\u5f0f -->\r\n<link href=\"".toCharArray();
    _jsp_string34 = "';\r\n			//\u6e20\u9053\u53ef\u89c1 \r\n			if($(\"#platforms\").is(\":visible\")){\r\n				var checkedIds = new Array();\r\n				var i = 0;\r\n				$(\".platformId\").each(function() {\r\n					if(this.checked == true) {\r\n						checkedIds[i] = this.value;\r\n						i++;\r\n					}\r\n				});\r\n				//alert(isCompare);\r\n				//alert(checkedIds);\r\n				if(i > 0){\r\n					if(isCompare == 0){\r\n						$(\"#channelIds\", window.parent.document).val(checkedIds);\r\n						$(\"#zoneIds\", window.parent.document).val(\"\");\r\n						$(\"#type\", window.parent.document).val(2);\r\n					}else if(isCompare == 1){\r\n						$(\"#compareChannelIds\", window.parent.document).val(checkedIds);\r\n						$(\"#compareZoneIds\", window.parent.document).val(\"\");\r\n						$(\"#compareType\", window.parent.document).val(2);\r\n					}\r\n					document.location.href=\"".toCharArray();
    _jsp_string24 = "\">".toCharArray();
    _jsp_string15 = "/scripts/artdialog/artDialog.js?skin=opera\"></script>\r\n\r\n<script src=\"".toCharArray();
    _jsp_string1 = "/images/logo2.png\" type=\"image/x-icon\" /> -->\r\n<title>".toCharArray();
    _jsp_string36 = "/bPlatformGameZone/bPlatformGameZone_getZonePlatforms.shtml?appId=\"+".toCharArray();
    _jsp_string5 = "/theme/default/css/bootstrap-theme.css\" rel=\"stylesheet\" type=\"text/css\">\r\n\r\n<!-- \u5168\u5c40\u6837\u5f0f -->\r\n<!-- \u9875\u9762\u6837\u5f0f -->\r\n<!-- bootstrap-switch\u5207\u6362\u5f00\u5173 -->\r\n<link href=\"".toCharArray();
    _jsp_string22 = "\r\n							".toCharArray();
    _jsp_string3 = "/js/bootstrap/css/bootstrap.min.css\" type=\"text/css\">\r\n<!-- font-awesome\u5b57\u4f53\u56fe\u6807 -->\r\n<link href=\"".toCharArray();
    _jsp_string6 = "/js/plugins/bootstrap-switch/stylesheets/bootstrap-switch.css\" rel=\"stylesheet\" type=\"text/css\">\r\n\r\n<!-- datetimepicker\u65e5\u671f\u65f6\u95f4\u9009\u62e9\u63d2\u4ef6 -->\r\n<link rel=\"stylesheet\" type=\"text/css\" href=\"".toCharArray();
    _jsp_string32 = "\r\n				</tbody>\r\n			 </table>\r\n		</div>	\r\n		<button type=\"button\" id=\"changeZone\" class=\"btn btn-primary \" onclick=\"sure();\"><i class=\"icon-ok\"></i> \u786e\u5b9a</button>\r\n		<button type=\"button\" id=\"changeZone\" class=\"btn btn-primary \" onclick=\"next();\"> \u4e0b\u4e00\u6b65</button>\r\n	</div>\r\n	<script type=\"text/javascript\">\r\n		$(document).ready(function() {\r\n			$(\"#zones\").show();\r\n			$(\"#checkedAll\").click(function(){\r\n				\r\n				if($(\"#platforms\").is(\":visible\")){		\r\n				$(\".platformId\").each(function() {\r\n					if($(\"#checkedAll\").prop(\"checked\")==true){\r\n						this.checked = true;\r\n					}else{\r\n						this.checked = false;\r\n					}\r\n				});\r\n				//\u533a\u670d\u4e0d\u9009\r\n				$(\".zoneId\").each(function() {\r\n						this.checked = false;\r\n				});\r\n				\r\n				}else{\r\n					$(\".zoneId\").each(function() {\r\n						if($(\"#checkedAll\").prop(\"checked\")==true){\r\n							this.checked = true;\r\n						}else{\r\n							this.checked = false;\r\n						}\r\n					});\r\n					//\u6e20\u9053\u4e0d\u9009 \r\n					$(\".platformId\").each(function() {					\r\n							this.checked = false;\r\n					});\r\n					\r\n				}\r\n			});	\r\n		});\r\n		\r\n		function change(){\r\n			if($(\"#platforms\").is(\":visible\")){\r\n				if($(\".platformId\").prop(\"checked\") ){\r\n					$(\"#checkedAll\").prop(\"checked\",false);\r\n				}else{\r\n					$(\"#checkedAll\").prop(\"checked\",true);\r\n				}\r\n				\r\n				$(\"#zones\").show();\r\n				$(\"#platforms\").hide();\r\n				\r\n				$(\"#changeZone\").addClass(\"btn-primary\");\r\n				$(\"#changePlatform\").removeClass(\"btn-primary\");\r\n			}else {\r\n				if($(\".zoneId\").prop(\"checked\")){ \r\n					$(\"#checkedAll\").prop(\"checked\",false);\r\n				}else{\r\n					$(\"#checkedAll\").prop(\"checked\",true);\r\n				}\r\n				\r\n				$(\"#zones\").hide();\r\n				$(\"#platforms\").show();\r\n				\r\n				$(\"#changeZone\").removeClass(\"btn-primary\");\r\n				$(\"#changePlatform\").addClass(\"btn-primary\");\r\n			}\r\n		}\r\n		\r\n		function sure(){\r\n			var isCompare = '".toCharArray();
    _jsp_string0 = "\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n<!doctype html>\r\n<html lang=\"zh-CN\">\r\n<head>\r\n	<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\"/>\r\n	<meta http-equiv=\"Cache-Control\" content=\"no-store\"/>\r\n	<meta name=\"viewport\" content=\"target-densitydpi=high-dpi,width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0, user-scalable=no\">\r\n	<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n	<meta http-equiv=\"Pragma\" content=\"no-cache\"/>\r\n	<meta http-equiv=\"Expires\" content=\"0\"/>\r\n	<meta name=\"description\" content=\"\">\r\n	<meta name=\"author\" content=\"qhq\">\r\n	<!-- <link rel=\"shortcut icon\" href=\"".toCharArray();
    _jsp_string31 = "\r\n                              ".toCharArray();
    _jsp_string14 = "/js/plugins/jquery-ui/themes/custom-theme/jquery-ui-1.10.0.custom.css\" />\r\n\r\n<script type=\"text/javascript\" src=\"".toCharArray();
    _jsp_string10 = "/js/plugins/messenger-master/css/messenger-theme-flat.css\" />\r\n<!-- \u4e0a\u4f20\u6587\u4ef6\u63d2\u4ef6 -->\r\n<link rel=\"stylesheet\" type=\"text/css\" href=\"".toCharArray();
    _jsp_string21 = "\r\n						<tr>\r\n							".toCharArray();
    _jsp_string12 = "/js/plugins/select2/select2.css\" />\r\n<link rel=\"stylesheet\" type=\"text/css\" href=\"".toCharArray();
    _jsp_string19 = "/styles/main.css\" rel=\"stylesheet\" type=\"text/css\">\r\n\r\n<!-- \u652f\u6301IE8 -->\r\n<!--[if lt IE 9]>\r\n  <script src=\"/js/html5shiv.js\"></script>\r\n  <script src=\"/js/respond.min.js\"></script>\r\n<![endif]-->\r\n<!-- \u4f4e\u4e8eIE8\u65f6\u63d0\u793a\u5347\u7ea7\u6d4f\u89c8\u5668 -->\r\n<!--[if lt IE 8]>\r\n  <script src=\"/js/update_ie/update_ie6.js\" ></script>           \r\n<![endif]-->\r\n\r\n</head>\r\n<body class=\"fix_top_nav_padding\">\r\n	<input type=\"hidden\" id=\"appId\" value=\"".toCharArray();
    _jsp_string2 = "</title>\r\n<!-- \u5168\u5c40\u6837\u5f0f -->\r\n<link rel=\"stylesheet\" href=\"".toCharArray();
    _jsp_string16 = "/js/jquery.js\"></script> \r\n<script src=\"".toCharArray();
    _jsp_string35 = "/bPlatformGameZone/bPlatformGameZone_getPlatformZones.shtml?appId=\"+appId+\"&platformIds=\"+checkedIds+\"&isCompare=\"+isCompare;\r\n				}else{\r\n					parent.errorTip(\"\u8bf7\u9009\u62e9\u6e20\u9053\uff01\");\r\n				}\r\n			}\r\n			//\u5206\u533a\u53ef\u89c1 \r\n			else{\r\n				var checkedIds = new Array();\r\n				var i = 0;\r\n				$(\".zoneId\").each(function() {\r\n					if(this.checked == true) {\r\n						checkedIds[i] = this.value;\r\n						i++;\r\n					}\r\n				});\r\n				//alert(isCompare);\r\n				//alert(checkedIds);\r\n				if(i > 0){\r\n					if(isCompare == 0){\r\n						$(\"#zoneIds\", window.parent.document).val(checkedIds);\r\n						$(\"#channelIds\", window.parent.document).val(\"\");\r\n						$(\"#type\", window.parent.document).val(1);\r\n					}else if(isCompare == 1){\r\n						$(\"#compareZoneIds\", window.parent.document).val(checkedIds);\r\n						$(\"#compareChannelIds\", window.parent.document).val(\"\");\r\n						$(\"#compareType\", window.parent.document).val(1);\r\n					}\r\n					document.location.href=\"".toCharArray();
    _jsp_string20 = "\">\r\n\r\n	<div class=\"panel-body \">\r\n		<button type=\"button\" id=\"changeZone\" class=\"btn btn-primary \" onclick=\"change();\"> &nbsp;&nbsp;\u533a&nbsp;\u670d&nbsp;&nbsp;</button>\r\n		<button id=\"changePlatform\"  type=\"button\" class=\"btn\" onclick=\"change();\"> &nbsp;&nbsp;\u6e20&nbsp;\u9053&nbsp;&nbsp;</button>\r\n        <label style=\"float: right\">\u5168 \u9009\uff1a<input type=\"checkbox\" id=\"checkedAll\"/></label>  \r\n\r\n		 \r\n		<br/>\r\n		<br/>\r\n		<div class=\"table-responsive\" style=\"min-height: 200px\">\r\n	 		<table class=\"table table-hover table-striped table-bordered table-condensed table-big\" style=\"display:none\" id=\"platforms\">	\r\n	   			<tbody>\r\n	   			<!-- <tr><td><label>\u5168 \u9009\uff1a</label><input type=\"checkbox\" id=\"checkedAll\"/></td></tr> -->\r\n					".toCharArray();
    _jsp_string17 = "/js/jquery-migrate-1.2.1.min.js\"></script> \r\n\r\n<!-- /\u9875\u9762\u6837\u5f0f -->\r\n<!-- \u81ea\u5b9a\u4e49\u6837\u5f0f -->\r\n<link href=\"".toCharArray();
    _jsp_string37 = "+\"&zoneIds=\"+checkedIds+\"&isCompare=\"+isCompare;\r\n				}else{\r\n					parent.errorTip(\"\u8bf7\u9009\u62e9\u5206\u533a\uff01\");\r\n				}\r\n			}\r\n		}\r\n	</script>\r\n</body>\r\n</html>".toCharArray();
    _jsp_string13 = "/js/plugins/select2/select2-bootstrap.css\" />\r\n<!--jquery UI\u63d2\u4ef6 -->\r\n<link rel=\"stylesheet\" type=\"text/css\" href=\"".toCharArray();
    _jsp_string8 = "/js/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css\" />\r\n<!-- messenger-master\u5f39\u51fa\u63d0\u793a\u4fe1\u606f\u63d2\u4ef6 -->\r\n<link rel=\"stylesheet\" type=\"text/css\" href=\"".toCharArray();
    _jsp_string28 = "\r\n				</tbody>\r\n			</table>\r\n			   		\r\n	 		<table class=\"table table-hover table-striped table-bordered table-condensed table-big\" style=\"display:none\" id=\"zones\">\r\n	  			<tbody>\r\n	  			<!-- <tr><td><label>\u5168 \u9009\uff1a</label><input type=\"checkbox\" id=\"checkedAll\"/></td></tr> -->\r\n					".toCharArray();
    _jsp_string7 = "/js/plugins/bootstrap-datetimepicker/css/datetimepicker.css\" />\r\n<link rel=\"stylesheet\" type=\"text/css\" href=\"".toCharArray();
    _jsp_string18 = "/theme/default/css/main.css\" rel=\"stylesheet\" type=\"text/css\">\r\n\r\n<!-- \u9879\u76ee\u81ea\u5b9a\u4e49\u6837\u5f0f -->\r\n<link href=\"".toCharArray();
    _jsp_string33 = "';\r\n			//\u6e20\u9053\r\n			if($(\"#platforms\").is(\":visible\")){\r\n				var checkedIds = new Array();\r\n				var i = 0;\r\n				$(\".platformId\").each(function() {\r\n					if(this.checked == true) {\r\n						checkedIds[i] = this.value;\r\n						i++;\r\n					}\r\n				});\r\n				//alert(isCompare);\r\n				if(i > 0){\r\n					if(isCompare == 0){\r\n						$(\"#channelIds\", window.parent.document).val(checkedIds);\r\n						$(\"#zoneIds\", window.parent.document).val(\"\");\r\n						$(\"#type\", window.parent.document).val(2);\r\n					}else if(isCompare == 1){\r\n						$(\"#compareChannelIds\", window.parent.document).val(checkedIds);\r\n						$(\"#compareZoneIds\", window.parent.document).val(\"\");\r\n						$(\"#compareType\", window.parent.document).val(2);\r\n					}\r\n					parent.searchAndValid();\r\n				}else{\r\n					parent.errorTip(\"\u8bf7\u9009\u62e9\u6e20\u9053\uff01\");\r\n				}\r\n			}\r\n			//\u5206\u533a \r\n			else{\r\n				var checkedIds = new Array();\r\n				var i = 0;\r\n				$(\".zoneId\").each(function() {\r\n					if(this.checked == true) {\r\n						checkedIds[i] = this.value;\r\n						i++;\r\n					}\r\n				});\r\n				//alert(isCompare);\r\n				if(i > 0){\r\n					if(isCompare == 0){\r\n						$(\"#zoneIds\", window.parent.document).val(checkedIds);\r\n						$(\"#channelIds\", window.parent.document).val(\"\");\r\n						$(\"#type\", window.parent.document).val(1);\r\n					}else if(isCompare == 1){\r\n						$(\"#compareZoneIds\", window.parent.document).val(checkedIds);\r\n						$(\"#compareChannelIds\", window.parent.document).val(\"\");\r\n						$(\"#compareType\", window.parent.document).val(1);\r\n					}\r\n					parent.searchAndValid();\r\n				}else{\r\n					parent.errorTip(\"\u8bf7\u9009\u62e9\u5206\u533a\uff01\");\r\n				}\r\n			}\r\n		}\r\n		\r\n		\r\n		function next(){\r\n			var appId = $(\"#appId\").val();\r\n			var isCompare = '".toCharArray();
    _jsp_string11 = "/js/plugins/bootstrap-fileupload/bootstrap-fileupload.css\" />\r\n<!-- \u6570\u636e\u52a0\u8f7d\u67e5\u8be2\u63d2\u4ef6 -->\r\n<link rel=\"stylesheet\" type=\"text/css\" href=\"".toCharArray();
    _jsp_string25 = "&nbsp;&nbsp;&nbsp;</td>\r\n							".toCharArray();
  }
}
