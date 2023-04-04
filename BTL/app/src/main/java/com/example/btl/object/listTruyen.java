package com.example.btl.object;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class listTruyen implements Serializable {
    private String tenTruyen, tenChap, urlAnh, ngayDang;
    private Integer slLike, slView, slChap;

    public listTruyen(){

    }

    public listTruyen(JSONObject o) throws JSONException {
        tenTruyen = o.getString("tenTruyen");
        tenChap = o.getString("tenChap");
        slChap = o.getInt("slChap");
        ngayDang = o.getString("ngayDang");
        urlAnh = o.getString("urlAnh");
        slLike = o.getInt("slLike");
        slView = o.getInt("slView");
    }

    public listTruyen(String tenTruyen, String tenChap, int slChap, String ngayDang, String urlAnh, int slLike, int slView) {
        this.tenTruyen = tenTruyen;
        this.tenChap = tenChap;
        this.slChap = slChap;
        this.ngayDang = ngayDang;
        this.urlAnh = urlAnh;
        this.slLike = slLike;
        this.slView = slView;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getTenChap() {
        return tenChap;
    }

    public void setTenChap(String tenChap) {
        this.tenChap = tenChap;
    }

    public Integer getSlChap() {
        return slChap;
    }

    public void setSlChap(Integer slChap) {
        this.slChap = slChap;
    }

    public String getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(String ngayDang) {
        this.ngayDang = ngayDang;
    }

    public String getUrlAnh() {
        return urlAnh;
    }

    public void setUrlAnh(String urlAnh) {
        this.urlAnh = urlAnh;
    }

    public Integer getSLLike() {
        return slLike;
    }

    public void setSlLike(Integer slLike) {
        this.slLike = slLike;
    }

    public Integer getSLView() {
        return slView;
    }

    public void setSlView(Integer slView) {
        this.slView = slView;
    }
}
