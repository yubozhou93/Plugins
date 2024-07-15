package cn.ac.cncb.ngdc.plugins.mapper;

import cn.ac.cncb.ngdc.plugins.pojo.Customs;
import cn.ac.cncb.ngdc.plugins.pojo.CustomsDetail;
import cn.ac.cncb.ngdc.plugins.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomsMapper {
    int selectAccessionCount(String accession);
    int saveCustoms(Customs customs);
    int saveCustomsDetail(CustomsDetail customsDetail);
}
