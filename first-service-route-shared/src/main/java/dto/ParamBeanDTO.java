package dto;

import java.time.LocalDate;

public interface ParamBeanDTO extends RouteDTO {

    Integer getId();
    LocalDate getCreationDate();
    String getOrderBy();
    Long getPageSize();
    Long getOffSet();

    void setId(Integer id);
    void setCreationDate(LocalDate creationDate);
    void setOrderBy(String orderBy);
    void setPageSize(Long pageSize);
    void setOffSet(Long offSet);
}
