package cn.ilmeyu.learn.spring.ioc.beans;

import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Author 余文楷
 * @date 2020/4/11 9:31 下午
 * @since 1.0.0
 **/
@Setter
public class CarFactoryBean implements FactoryBean<Car> {

    private String carInfo;

    @Override
    public Car getObject( ) {
        Car car = new Car();
        String[] infos = carInfo.split( "," );
        car.setBrand( infos[0] );
        car.setMaxSpeed( Integer.parseInt( infos[1] ) );
        car.setPrice( Double.parseDouble( infos[2] ) );
        return car;
    }

    @Override
    public boolean isSingleton( ) {
        return false;
    }

    @Override
    public Class<?> getObjectType( ) {
        return Car.class;
    }

}
