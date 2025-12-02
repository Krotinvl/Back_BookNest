package com.booknest.dto;

public class UpdatePagesDto {
    private Integer pages;

    public UpdatePagesDto() {}

    public UpdatePagesDto(Integer pages) { this.pages = pages; }

    public Integer getPages() { return pages; }
    public void setPages(Integer pages) { this.pages = pages; }
}
