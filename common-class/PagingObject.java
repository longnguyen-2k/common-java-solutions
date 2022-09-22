import java.io.Serializable;

public class PagingObject implements Serializable {
    private Integer totalsRecord;
    private Integer indexPage;
    private Integer maxPerPage;
    private Integer totalPages;

    public Integer getTotalsRecord() {
        return totalsRecord;
    }

    public void setTotalsRecord(Integer totalsRecord) {
        this.totalsRecord = totalsRecord;
        updateTotalPages();
    }

    public Integer getIndexPage() {
        return indexPage;
    }

    public void setIndexPage(Integer indexPage) {
        this.indexPage = indexPage;
    }

    public Integer getMaxPerPage() {
        return maxPerPage;
    }

    public void setMaxPerPage(Integer maxPerPage) {
        this.maxPerPage = maxPerPage;
        updateTotalPages();
    }

    public Integer getTotalPages() {
        return totalPages;
    }

   private void updateTotalPages(){
       if(this.totalsRecord>0 && maxPerPage>0){
           this.totalPages=totalsRecord/maxPerPage;
       }else {
           this.totalPages=0;
       }
   }
}
