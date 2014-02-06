package dream.keel.test.spring;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import dream.keel.base.BaseModel;
import dream.keel.base.BaseService;
import dream.keel.base.Page;
import dream.keel.util.TestUtils;

@RunWith(Parameterized.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class TestService<T extends BaseModel<?>> {
	public ApplicationContext context = null;

	private BaseService<T> baseService = null;

	@Parameter(0)
	public Long id;
	@Parameter(1)
	public T t1 = null;
	@Parameter(2)
	public T t2 = null;
	@Parameter(3)
	public List<T> lt1 = null;
	@Parameter(4)
	public List<T> lt2 = null;
	@Parameter(5)
	public Page<T> p = null;

	public BaseService<T> getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService<T> baseService) {
		this.baseService = baseService;
	}

	public TestService() {
		this(null, null);
		System.out.println(">>>>>     一个新的TestService实例     <<<<<");
	}

	public TestService(BaseService<T> baseService) {
		this.baseService = baseService;
	}

	@SuppressWarnings("unchecked")
	public TestService(ApplicationContext context, BaseService<T> baseService) {
		this.context = context == null ? TestUtils.getApplicationContext() : context;
		this.baseService = baseService == null ? (BaseService<T>) this.context.getBean(getBeanName()) : baseService;
	}

	protected abstract String getBeanName();

	@Test
	public void testSaveOne() {
		if (t1.getId() != null) {
			fail("被测试的数据的ID为Null，无法测试！");
			return;
		}

		Throwable t = null;
		int num = 0;
		try {
			num = this.getBaseService().saveOne(t1);
		} catch (Exception ex) {
			t = ex;
		}
		assertNull("保存数据发生异常！", t);
		assertSame("数据更新成功！数据库应该没有的id为" + t1.getId() + "的数据被更新了！", 0, num);

		t = null;
		t2.setId(t1.getId());
		t1.setId(null);
		try {
			num = this.getBaseService().saveOne(t1);
		} catch (Exception ex) {
			t = ex;
		}
		assertNull("保存数据发生异常！", t);
		assertSame("保存数据库失败！", 1, num);

		t = null;
		try {
			num = this.getBaseService().saveOne(t1);
		} catch (Exception ex) {
			t = ex;
		}
		assertNotNull("保存数据应该发生异常！", t);
		assertTrue("保存数据发生其它的异常！", t instanceof SQLException);

		t = null;
		try {
			num = this.getBaseService().saveOne(t2);
		} catch (Exception ex) {
			t = ex;
		}
		assertNull("更新数据发生异常！", t);
		assertSame("更新数据库失败！", 1, num);
	}

	@Test
	public void saveOneSelective() {
		if (t1.getId() != null) {
			fail("被测试的数据的ID为Null，无法测试！");
			return;
		}

		Throwable t = null;
		int num = 0;
		try {
			num = this.getBaseService().saveOne(t1);
		} catch (Exception ex) {
			t = ex;
		}
		assertNull("保存数据发生异常！", t);
		assertSame("数据更新成功！数据库应该没有的id为" + t1.getId() + "的数据被更新了！", 0, num);

		t = null;
		t2.setId(t1.getId());
		t1.setId(null);
		try {
			num = this.getBaseService().saveOne(t1);
		} catch (Exception ex) {
			t = ex;
		}
		assertNull("保存数据发生异常！", t);
		assertSame("保存数据库失败！", 1, num);

		t = null;
		try {
			num = this.getBaseService().saveOne(t1);
		} catch (Exception ex) {
			t = ex;
		}
		assertNotNull("保存数据应该发生异常！", t);
		assertTrue("保存数据发生其它的异常！", t instanceof SQLException);

		t = null;
		try {
			num = this.getBaseService().saveOne(t2);
		} catch (Exception ex) {
			t = ex;
		}
		assertNull("更新数据发生异常！", t);
		assertSame("更新数据库失败！", 1, num);
	}

	public abstract void saveOneSelective_assert();

	@Test
	public void saveList1() {
		int num = this.getBaseService().saveList(lt1);
		assertSame("保存数据库失败！", lt1.size(), num);
	}

	@Test
	public void saveList2() {
		Throwable t = null;
		try {
			this.getBaseService().saveList(lt2);
		} catch (Exception ex) {
			t = ex;
		}
		assertNotNull("保存数据应该发生异常！", t);
		
		List<T> list = this.getBaseService().queryByList(lt2);
		assertSame("保存数据没有事务，发生异常不能作到回滚！",0,list.size());
	}

	@Test
	public void saveListSelective1() {
		int num = this.getBaseService().saveListSelective(lt1);
		assertSame("保存数据库失败！", lt1.size(), num);
	}

	@Test
	public void saveListSelective2() {
		Throwable t = null;
		try {
			this.getBaseService().saveListSelective(lt2);
		} catch (Exception ex) {
			t = ex;
		}
		assertNotNull("保存数据应该发生异常！", t);
		
		List<T> list = this.getBaseService().queryByList(lt2);
		assertSame("保存数据没有事务，发生异常不能作到回滚！",0,list.size());
	}

	@Test
	public void deleteOne() {
		int num = this.getBaseService().deleteOne(t1.getId());
		assertSame("保存数据库失败！", 1, num);
	}

	@Test
	public void deleteList1() {
		int num = this.getBaseService().deleteList(lt1);
		assertSame("保存数据库失败！", lt1.size(), num);
	}

	@Test
	public void deleteList2() {
		Throwable t = null;
		try {
			this.getBaseService().deleteList(lt2);
		} catch (Exception ex) {
			t = ex;
		}
		assertNotNull("保存数据应该发生异常！", t);
		
		List<T> list = this.getBaseService().queryByList(lt2);
		assertSame("保存数据没有事务，发生异常不能作到回滚！",0,list.size());
	}

	@Test
	public void queryByPage() {
		this.getBaseService().queryByPage(p);
		assertNotNull("无法获取目标数据！", p.getResult());
	}

	@Ignore
	@Test
	public void queryFull() {

	}

	@Ignore
	@Test
	public void queryFullByPage() {

	}

	@Ignore
	@Test
	public void queryFullByParameters() {

	}

	@Ignore
	@Test
	public void queryFullAll() {

	}
}
