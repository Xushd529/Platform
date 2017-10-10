package com.maizhong.platform.service.impl;

import com.maizhong.platform.dto.NavsDto;
import com.maizhong.platform.mapper.MNavsMapper;
import com.maizhong.platform.pojo.MNavs;
import com.maizhong.platform.pojo.MNavsExample;
import com.maizhong.platform.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xushd on 2017/10/9.
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MNavsMapper mNavsMapper;

    /**
     * Manage 获取 navs
     * @return
     */
    @Override
    public List<NavsDto> getManageMenu() {

        return getMnavsList(0);
    }

    private List<NavsDto> getMnavsList(long parentId){

        List<NavsDto> list = new ArrayList<>();
        MNavsExample mnavEx = new MNavsExample();
        mnavEx.createCriteria().andDelflagEqualTo(0).andStatusEqualTo(1).andParentIdEqualTo(parentId);
        List<MNavs> mNavs = mNavsMapper.selectByExample(mnavEx);
        for (MNavs mNav : mNavs) {
            NavsDto dto = new NavsDto();
            dto.setTitle(mNav.getTitle());
            dto.setHref(mNav.getHref());
            dto.setIcon(mNav.getIcon());
            dto.setSpread(false);
            List<NavsDto> mnavsList = this.getMnavsList(mNav.getId());
            if(mnavsList.size()>0){
                dto.setChildren(mnavsList);
            }
            list.add(dto);
        }
        return list;
    }
}
