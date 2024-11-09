package pers.dao;

import org.springframework.stereotype.Repository;
import pers.domain.Comment;
import pers.domain.GoodsItem;
import tk.mybatis.mapper.common.Mapper;


@Repository
public interface GoodsItemMapper extends Mapper<GoodsItem> {
}