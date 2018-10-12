package core.module.orm.ibatis;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.util.Assert;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.item.utils.CookieUtils;

import core.module.annotation.ModifyLog;
import core.module.orm.MapBean;
import core.module.orm.Page;
import core.module.utils.ReflectionUtils;


/**
 * 继承SqlMapClientDaoSupport的DAO泛型基类. 可在Service层直接使用, 也可以扩展泛型DAO子类使用.
 * 
 * @author yjli
 * @since 2010-03-20
 */
public class SqlMapClientDao<T, PK extends Serializable> {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	protected SqlMapClientTemplate sqlMapClientTemplate;

	protected Class<T> entityClass;

	/**
	 * 用于Dao层子类使用的构造函数, 通过子类的泛型定义取得对象类型Class.
	 */
	public SqlMapClientDao() {
		this.entityClass = ReflectionUtils.getSuperClassGenricType(getClass());
	}

	/**
	 * 用于用于省略Dao层, 在Service层直接使用通用SqlMapClientDao的构造函数, 在构造函数中定义对象类型Class.
	 */
	public SqlMapClientDao(final SqlMapClientTemplate sqlMapClientTemplate, final Class<T> entityClass) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
		this.entityClass = entityClass;
	}

	@Autowired
	public void setSqlMapClientTemplate(SqlMapClientTemplate sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

	/**
	 * 保存对象.
	 * 
	 * @param statementName 声明
	 * @param parameterObject 参数
	 */
	public Object save(final String statementName, final Object parameterObject) {
		Assert.notNull(statementName, "statementName不能为空");
		
		Object o=sqlMapClientTemplate.insert(statementName, parameterObject);
		if(o==null)
			return "-1";
		else
			return o;
	}

	/**
	 * 修改对象.
	 * 
	 * @param statementName 声明
	 * @param parameterObject 参数
	 */
	public int update(final String statementName, final Object parameterObject) {
		ModifyLog.ModifyLog(parameterObject);
		Assert.notNull(statementName, "statementName不能为空");
		return sqlMapClientTemplate.update(statementName, parameterObject);
	}
	
	/**
	 * 修改对象.没有修改记录
	 * 
	 * @param statementName 声明
	 * @param parameterObject 参数
	 */
	public int updateNoModifyLog(final String statementName, final Object parameterObject) {
		Assert.notNull(statementName, "statementName不能为空");
		return sqlMapClientTemplate.update(statementName, parameterObject);
	}

	/**
	 * 删除对象.
	 * 
	 * @param statementName 声明
	 * @param parameterObject 参数
	 */
	public int delete(final String statementName, final Object parameterObject) {
		Assert.notNull(statementName, "statementName不能为空");
		return sqlMapClientTemplate.delete(statementName, parameterObject);
	}

	/**
	 * 按条件查询唯一对象.
	 * 
	 * @param statementName 声明
	 * @param parameterObject 参数
	 */
	@SuppressWarnings("unchecked")
	public T get(final String statementName, final Object parameterObject) {
		Assert.notNull(statementName, "statementName不能为空");
		return (T) sqlMapClientTemplate.queryForObject(statementName, parameterObject);
	}

	/**
	 * 按条件查询唯一对象.
	 * 
	 * @param statementName 声明
	 * @param parameterObject 参数
	 */
	@SuppressWarnings("unchecked")
	public <X> X findUnique(final String statementName, final Object parameterObject) {
		Assert.notNull(statementName, "statementName不能为空");
		return (X) sqlMapClientTemplate.queryForObject(statementName, parameterObject);
	}
	
	/**
	 * 按条件查询对象列表.
	 * 
	 * @param statementName 声明
	 * @param parameterObject 参数
	 */
	@SuppressWarnings("unchecked")
	public List<T> find(final String statementName, final Object parameterObject) {
		Assert.notNull(statementName, "statementName不能为空");
		return sqlMapClientTemplate.queryForList(statementName, parameterObject);
	}

	/**
	 * 执行count查询获得对象总数.
	 * 
	 * @param statementName 声明
	 * @param parameterObject 参数
	 */
	public long countResult(final String statementName, final Object parameterObject) {
		Assert.notNull(statementName, "statementName不能为空");	
		try {
			Object object =  sqlMapClientTemplate.queryForObject(statementName, parameterObject);
			if (object==null)
				return 0l;
			else
				return (Long)object;
		} catch (Exception e) {
			throw new RuntimeException("Can not be auto count, statementName is: " + statementName, e);
		}
	}

	/**
	 * 分页查询对象列表.
	 * 
	 * @param page 分页对象
	 * @param mb 搜索条件
	 * @param countStatementName 总记录数声明
	 * @param indexStatementName 对象列表声明
	 * @return Page<T>
	 */
	public Page<T> find(Page<T> page, final MapBean mb, final String countStatementName, final String indexStatementName) {
		//获取cookie保存的page size
		page = CookieUtils.setCookiePageSize(page);
		Assert.notNull(page, "page不能为空");
		Assert.notNull(mb, "mb不能为空");
		Assert.notNull(countStatementName, "countStatementName不能为空");
		Assert.notNull(indexStatementName, "indexStatementName不能为空");

		if (page.isAutoCount()) {
			long totalCount = countResult(countStatementName, mb);
			page.setTotalCount(totalCount);
		}

		// 分页参数
		mb.put("firstResult", page.getFirstResult());
		mb.put("pageSize", page.getPageSize());

		List<T> result = find(indexStatementName, mb);
		page.setResult(result);
		return page;
	}

	/**
	 * 判断对象的属性值在数据库内是否唯一.
	 * 
	 * 在修改对象的情景下, 如果属性新修改的值(value)等于属性原来的值(orgValue)则不作比较.
	 */
	public boolean isPropertyUnique(final String statementName, final MapBean mb) {	
		String newValue = mb.getString("newValue");
		String oldValue = mb.getString("oldValue");		
		if (newValue == null || newValue.equals(oldValue))
			return true;

		Long count = findUnique(statementName, mb);
		if (count == null)
			count = 0L;

		return (count == 0L);
	}
	
	@SuppressWarnings("unchecked")
	public <X> Page<X> query(final Page<X> page, final MapBean mb, final String countStatementName, final String indexStatementName) {
		Assert.notNull(page, "page不能为空");
		Assert.notNull(mb, "mb不能为空");
		Assert.notNull(countStatementName, "countStatementName不能为空");
		Assert.notNull(indexStatementName, "indexStatementName不能为空");

		if (page.isAutoCount()) {
			long totalCount = countResult(countStatementName, mb);
			page.setTotalCount(totalCount);
		}

		// 分页参数
		mb.put("firstResult", page.getFirstResult());
		mb.put("pageSize", page.getPageSize());

		List<X> result = sqlMapClientTemplate.queryForList(indexStatementName, mb);
		page.setResult(result);
		return page;
	}
	
	@SuppressWarnings("unchecked")
	public <X> Page<X> query(final Page<X> page, final Object obj, final String countStatementName, final String indexStatementName) {
		Assert.notNull(page, "page不能为空");
		Assert.notNull(obj, "obj不能为空");
		Assert.notNull(countStatementName, "countStatementName不能为空");
		Assert.notNull(indexStatementName, "indexStatementName不能为空");

		if (page.isAutoCount()) {
			long totalCount = countResult(countStatementName, obj);
			page.setTotalCount(totalCount);
		}

		List<X> result = sqlMapClientTemplate.queryForList(indexStatementName, obj);
		page.setResult(result);
		return page;
	}
	
	@SuppressWarnings("unchecked")
	public <X> List<X> query(final MapBean mb, final String indexStatementName) {
		Assert.notNull(mb, "mb不能为空");
		Assert.notNull(indexStatementName, "indexStatementName不能为空");
		return sqlMapClientTemplate.queryForList(indexStatementName, mb);
	}
	
	/**
	 * 批量插入数据
	 * @param statementName
	 * @param list
	 * @return
	 */
	public Object bulkInsert(final String statementName, final List<T> list){
		return sqlMapClientTemplate.execute(new SqlMapClientCallback(){
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();
				int batch = 0;
				for(Object object : list){
					executor.insert(statementName, object);
					batch++;
					if(batch == 100){
						executor.executeBatch();
						batch = 0;
					}
				}
				executor.executeBatch();
				return 1;
			}
		});
	}
	
	/**
	 * 批量更新数据
	 * @param statementName
	 * @param list
	 * @return
	 */
	public Object bulkUpdate(final String statementName, final List<T> list){
		return sqlMapClientTemplate.execute(new SqlMapClientCallback(){
			public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException {
				executor.startBatch();
				int batch = 0;
				for(Object object : list){
					executor.update(statementName, object);
					batch++;
					if(batch == 100){
						executor.executeBatch();
						batch = 0;
					}
				}
				executor.executeBatch();
				return 1;
			}
		});
	}
}