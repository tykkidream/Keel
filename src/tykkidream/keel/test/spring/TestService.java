package tykkidream.keel.test.spring;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import tykkidream.keel.base.BaseModel;
import tykkidream.keel.base.BaseService;
import tykkidream.keel.base.Page;
import tykkidream.keel.util.TestUtils;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class TestService<T extends BaseModel<?>> {

	@Parameter(0)
	public T t1 = null;
	@Parameter(1)
	public T t2 = null;
	@Parameter(2)
	public List<T> lt1 = null;
	@Parameter(3)
	public List<T> lt2 = null;
	@Parameter(4)
	public Page<T> p = null;

	public abstract BaseService<T> getBaseService();

	public abstract void setBaseService(BaseService<T> baseService);

	protected abstract String getBeanName();
	
	@Before
	@SuppressWarnings("unchecked")
	public void setUp(){
		this.setBaseService((BaseService<T>) TestUtils.getApplicationContext().getBean(getBeanName()));
	}

	@Test
	public void test01SaveOne() {
		// 第一次保存：向数据库中insert此数据，受影响行数应为1。
		int num = 0;
		t1.setId(null);
		num = this.getBaseService().saveOne(t1);
		assertSame("数据更新成功！数据库应该没有的id为" + t1.getId() + "的数据被更新了！", 1, num);

		// 第二次保存：数据库中应该没有此数据，受影响行数应为1。
		num = this.getBaseService().saveOne(t2);
		assertSame("保存数据库失败！", 0, num);
	}

	@Test
	public void test02SaveOneSelective() {
		if (t2.getId() == null) {
			fail("被测试的数据的ID为Null，无法测试！");
			return;
		}

		// 第一次保存：数据库中应该没有此数据，受影响行数应为0。
		Throwable t = null;
		int num = 0;
		try {
			num = this.getBaseService().saveOneSelective(t2);
		} catch (Exception ex) {
			t = ex;
		}
		assertNull("保存数据发生异常！", t);
		assertSame("数据更新成功！数据库应该没有的id为" + t2.getId() + "的数据被更新了！", 0, num);

		// 第二次保存：向数据库中insert此数据，受影响行数应为1。
		t = null;
		long id = t2.getId();
		t2.setId(null);
		try {
			num = this.getBaseService().saveOneSelective(t2);
		} catch (Exception ex) {
			t = ex;
		}
		long nid = t2.getId();
		t2.setId(id);
		assertNull("保存数据发生异常！", t);
		assertSame("保存数据库失败！", 1, num);
		assertNotNull("保存数据库失败！", nid);

		// 第三次保存：向数据库中update此数据，受影响行数应为1。
		t = null;
		t2.setId(nid);
		try {
			num = this.getBaseService().saveOneSelective(t2);
		} catch (Exception ex) {
			t = ex;
		}
		assertNull("更新数据发生异常！", t);
		assertSame("更新数据库失败！", 1, num);
	}

	protected abstract void testSaveOneSelective_assert();

	@Test
	public void test03SaveList1() {
		int num = this.getBaseService().saveList(lt1);
		
		assertSame("保存数据库失败！", lt1.size(), num);
	}

	@Test
	public void test04SaveList2() {
		int count1 = this.getBaseService().queryAll().size();
		Throwable t = null;
		try {
			this.getBaseService().saveList(lt2);
		} catch (Exception ex) {
			t = ex;
		}
		assertNotNull("保存数据应该发生异常！", t);

		int count2 = this.getBaseService().queryAll().size();
		assertSame("保存数据没有事务，发生异常不能作到回滚！",count1,count2);
	}

	@Test
	public void test05SaveListSelective1() {
		int num = this.getBaseService().saveListSelective(lt1);

		assertSame("保存数据库失败！", lt1.size(), num);
	}

	@Test
	public void test06SaveListSelective2() {
		int count1 = this.getBaseService().queryAll().size();
		Throwable t = null;
		try {
			this.getBaseService().saveListSelective(lt2);
		} catch (Exception ex) {
			t = ex;
		}
		assertNotNull("保存数据应该发生异常！", t);
		
		int count2 = this.getBaseService().queryAll().size();
		assertSame("保存数据没有事务，发生异常不能作到回滚！",count1,count2);
	}

	@Test
	public void test07DeleteOne() {
		int num = this.getBaseService().deleteOne(t2.getId());
		assertSame("保存数据库失败！", 1, num);
	}

	@Test
	public void test08DeleteList1() {
		int count1 = this.getBaseService().queryAll().size();
		
		Throwable t = null;
		try {
			this.getBaseService().deleteList(lt1);
		} catch (Exception ex) {
			t = ex;
		}
		assertNotNull("保存数据应该发生异常！", t);
		
		int count2 = this.getBaseService().queryAll().size();
		assertSame("保存数据库失败！", count1, count2);
	}

	@Test
	public void test09DeleteList2() {
		Throwable t = null;
		try {
			this.getBaseService().deleteList(lt2);
		} catch (Exception ex) {
			t = ex;
		}
		assertNotNull("保存数据应该发生异常！", t);
		
		List<T> list = this.getBaseService().queryByList(lt2);
		assertNotSame("保存数据没有事务，发生异常不能作到回滚！",lt2.size(),list.size());
	}

	@Test
	public void test10QueryByPage() {
		this.getBaseService().queryByPage(p);
		assertNotNull("无法获取目标数据！", p.getResult());
		
		if (p.getTotalPage() > 1) {
			assertSame("分页数据数量不正确！",p.getPageSize(),p.getResult().size());
		} else {
			assertSame("分页数据数量不正确！",p.getTotalRecord(),p.getResult().size());
		}
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
