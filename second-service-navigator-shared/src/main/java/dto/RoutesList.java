package dto;

import model.Routeeeeeeee;

import java.io.Serializable;
import java.util.List;

public class RoutesList implements Serializable {
    List<Routeeeeeeee> routes;
    private Integer totalSize;

    public List<Routeeeeeeee> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Routeeeeeeee> routes) {
        this.routes = routes;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }
}
