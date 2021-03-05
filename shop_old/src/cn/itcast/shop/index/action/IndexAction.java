package cn.itcast.shop.index.action;

import java.util.List;

import cn.striner.shop.category.service.CategoryService;
import cn.striner.shop.category.vo.Category;
import cn.striner.shop.product.service.ProductService;
import cn.striner.shop.product.vo.Product;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 棣栭〉璁块棶鐨凙ction
 * @author 浼犳櫤.閮槈
 *
 */
public class IndexAction extends ActionSupport{
	// 娉ㄥ叆涓�绾у垎绫荤殑Service:
	private CategoryService categoryService;
	// 娉ㄥ叆鍟嗗搧鐨凷ervice
	private ProductService productService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	/**
	 * 鎵ц鐨勮闂椤电殑鏂规硶:
	 */
	public String execute(){
		// 鏌ヨ鎵�鏈変竴绾у垎绫婚泦鍚�
		List<Category> cList = categoryService.findAll();
		// 灏嗕竴绾у垎绫诲瓨鍏ュ埌Session鐨勮寖鍥�:
		ActionContext.getContext().getSession().put("cList", cList);
		// 鏌ヨ鐑棬鍟嗗搧:
		List<Product> hList = productService.findHot();
		// 淇濆瓨鍒板�兼爤涓�:
		ActionContext.getContext().getValueStack().set("hList", hList);
		// 鏌ヨ鏈�鏂板晢鍝�:
		List<Product> nList = productService.findNew();
		// 淇濆瓨鍒板�兼爤涓�:
		ActionContext.getContext().getValueStack().set("nList", nList);
		return "index";
	}
	
	
}
