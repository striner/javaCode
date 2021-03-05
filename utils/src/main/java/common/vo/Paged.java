package common.vo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Paged<T> {

    private static Logger logger = LoggerFactory.getLogger(Paged.class);

    private int pageNo = 1;   //当前页数
    private int totalPages = 1;   //总页数
    private int pageSize = 10;     //每页显示行数
    private int totalRows;    //总行数
    private List<T> data;       //页面所存的数据


    public Paged() {}

    /**
     * Paged
     * @param pageNo 当前页数
     * @param totalPages 总页数
     */
    public Paged(int pageNo, int totalPages) {
        this.setTotalPages(totalPages);
        this.setPageNo(pageNo);
    }

    /**
     * Paged
     * @param data 数据
     */
    public Paged(List<T> data) {
        setData(data);
    }

    /**
     * Paged
     * @param pageSize 每页显示的行数
     * @param data 数据
     */
    public Paged(int pageSize, List<T> data) {
        setPageSize(pageSize);   //每页显示的行数
        setData(data);
    }

    /**
     * Paged
     * @param pageNo 当前页数
     * @param pageSize 每页显示的行数
     * @param data 数据
     */
    public Paged(int pageNo, int pageSize, List<T> data) {
        setPageSize(pageSize);   //每页显示的行数
        setData(data);
        setPageNo(pageNo);   //当前页数
    }

    /**
     * Paged
     * @param pageNo 当前页数
     * @param totalPages 总页数 小则裁尾部，大则为null
     * @param pageSize 每页显示的行数
     * @param data 数据
     */
    public Paged(int pageNo, int totalPages, int pageSize, List<T> data) {
        setPageSize(pageSize);   //每页显示的行数
        setData(data);
        setPageNo(pageNo);   //当前页数
        setTotalPages(totalPages);
    }




    /**
     * 判断是否有下一页
     * @return boolean
     */
    public boolean haveNextPage() {
        boolean ret;
        if (this.pageNo < this.totalPages) {
            ret = true;
        } else {
            ret = false;
            logger.error("There is no next page, it is already the last page！");
        }
        return ret;
    }

    /**
     * 判断是否有上一页
     * @return boolean
     */
    public boolean havePrevPage() {
        boolean ret;
        if (this.pageNo <= 1) {
            ret = false;
            logger.error("There is no previous page, it is already the first page！");
        } else {
            ret = true;
        }
        return ret;
    }

    /**
     * 获取下一页页码
     * @return int
     */
    public int getNextPageNo() {
        return haveNextPage() ? this.pageNo + 1 : this.pageNo;
    }

    /**
     * 获取上一页页码
     * @return int
     */
    public int getPrevPageNo() {
        return havePrevPage() ? this.pageNo - 1 : this.pageNo;
    }

    /**
     * 获取当前页页码
     * @return int
     */
    public int getPageNo() {
        return this.pageNo <= 0 ? 1 : this.pageNo;
    }

    /**
     * 设置当前页页码
     * @param pageNo
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo > 1 ? (pageNo > totalPages? totalPages : pageNo) : 1;
    }

    /**
     * 获取数据
     * @return List<T>
     */
    public List<T> getData() {
        return this.data;
    }

    /**
     * 设置数据
     * @return void
     */
    public void setData(List<T> data) {
        this.data = new ArrayList<>(data);
        this.totalRows = data.size();
        this.totalPages = this.totalRows%pageSize==0 ? this.totalRows/pageSize : this.totalRows/pageSize+1;
    }

    /**
     * 获取总行数
     * @return
     */
    public int getTotalRows() {
        return totalRows;
    }

    /**
     * 获取总页数
     * @return
     */
    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * 获取每页显示的行数
     * @return
     */
    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        if (totalRows > 0) {  //未传data时，totalRows为0
            totalPages = totalRows%pageSize==0 ? totalRows/pageSize : totalRows/pageSize+1;
        }
    }


    @Override
    public String toString() {

        StringBuffer dataInfo = new StringBuffer();
        if (data != null && !data.isEmpty()) {
            for (int i = 0; i < data.size()-1; i++) {
                dataInfo.append(data.get(i) + ", ");
            }
            dataInfo.append(data.get(data.size()-1));
        }

        return "Paged{"
                + (pageNo>0 ? " pageNo=" + pageNo + " " : "")
                + (totalPages>0 ? " totalPages=" + totalPages + " " : "")
                + (pageSize>0 ? " pageSize=" + pageSize + " " : "")
                + (totalRows>0 ? " totalRows=" + totalRows + " " : "")
                + (data == null||data.isEmpty() ? " " : "data=[" + dataInfo.toString() + "] ")
                + '}';
    }
}

