package cn.ac.cncb.ngdc.plugins.pojo;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
@Data
public class Customs {

    public String accession;

    public String title;
    public List<CustomsDetail> customsDetail;

}
