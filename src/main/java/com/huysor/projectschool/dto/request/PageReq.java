package com.huysor.projectschool.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PageReq {
   private Integer pageIndex;
   private Integer pageSize;
}
