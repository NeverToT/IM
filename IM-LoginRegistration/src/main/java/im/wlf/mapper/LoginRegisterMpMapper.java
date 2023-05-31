package im.wlf.mapper;

import im.wlf.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wlf
 * @since 2023-05-21
 */
@Mapper
public interface LoginRegisterMpMapper extends BaseMapper<User> {

}

