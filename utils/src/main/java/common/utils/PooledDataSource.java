package common.utils;

import com.mchange.v2.c3p0.AbstractComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.PostConstruct;
import javax.naming.Referenceable;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Properties;

/**
 * 数据库连接池
 *
 * Created by mulin on 2018/09/06.
 * In this class, the convention is greater than the configuration.
 *
 * Detailed description of the parameters in this class:
 *  dbConfigPath: 数据库配置文件的路径。
 *  db.driver(default:"com.mysql.jdbc.Driver"):使用的数据库驱动。
 *  db.url:数据库的url。
 *  db.userName:连接的数据库的同户名。
 *  db.password:连接的数据库的密码。
 *  db.maxPoolSize(default:"100"):连接池中保留的最大连接数。
 *  db.minPoolSize(default:"10"):连接池中保留的最小连接数。
 *  db.initialPoolSize(default:"20"):初始化时获取的连接。
 *  db.maxIdleTime(default:"3500"):最大空闲时间。
 *  db.autoCommitOnClose(default:"false"):关闭连接后不自动commit。
 *  db.acquireIncrement(default:"3"):当连接池中的连接耗尽的时候c3p0一次同时获取的连接数。
 *  db.checkoutTimeout(default:"1000"):连接超时时间。
 *  db.acquireRetryAttempts( default:"10"):当获取连接失败重试次数。
 *
 * 注意：若需使用本工具，请注意配置文件与本类中定义的字段名是否一致。
 */
public final class PooledDataSource extends AbstractComboPooledDataSource implements Serializable, Referenceable {
    private static final Logger logger = LoggerFactory.getLogger(PooledDataSource.class);
    private static final long serialVersionUID = 1;
    private static final short VERSION = 0x0002;

    @PostConstruct
    public void init(String dbConfigPath) throws PropertyVetoException, IOException {
        logger.info("PooledDataSource init...  dbConfigPath："+dbConfigPath);
        initConfig(dbConfigPath);
    }

    public PooledDataSource() {
        super();
    }

    public PooledDataSource(boolean autoregister) {
        super(autoregister);
    }

    public PooledDataSource(String configName) {
        super(configName);
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.writeShort(VERSION);
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        short version = ois.readShort();
        switch (version) {
            case VERSION:
                //ok
                break;
            default:
                throw new IOException("Unsupported Serialized Version: " + version);
        }
    }

    private void initConfig(String dbConfigPath) throws PropertyVetoException, IOException {
        Properties pro = PropertiesUtil.fromFile(dbConfigPath);

        setDriverClass(pro.getProperty("db.driver", "com.mysql.jdbc.Driver"));
        setJdbcUrl(pro.getProperty("db.url"));
        setUser(pro.getProperty("db.userName"));
        setPassword(pro.getProperty("db.password"));

        //连接池中保留的最大连接数
        setMaxPoolSize(Integer.parseInt(pro.getProperty("db.maxPoolSize", "100")));
        //连接池中保留的最小连接数
        setMinPoolSize(Integer.parseInt(pro.getProperty("db.minPoolSize", "10")));
        //初始化时获取的连接
        setInitialPoolSize(Integer.parseInt(pro.getProperty("db.initialPoolSize", "20")));
        //最大空闲时间
        setMaxIdleTime(Integer.parseInt(pro.getProperty("db.maxIdleTime", "3500")));
        //关闭连接后不自动commit
        setAutoCommitOnClose(Boolean.valueOf(pro.getProperty("db.autoCommitOnClose", "false")));
        //当连接池中的连接耗尽的时候c3p0一次同时获取的连接数
        setAcquireIncrement(Integer.parseInt(pro.getProperty("db.acquireIncrement", "3")));
        //获取连接超时时间
        setCheckoutTimeout(Integer.parseInt(pro.getProperty("db.checkoutTimeout", "10000")));
        //当获取连接失败重试次数
        setAcquireRetryAttempts(Integer.parseInt(pro.getProperty("db.acquireRetryAttempts", "10")));
    }
}

