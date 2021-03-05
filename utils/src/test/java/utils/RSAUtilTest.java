package utils;

import common.utils.RSAUtil;
import common.utils.RadixUtil;
import org.junit.Test;
import java.security.interfaces.RSAPublicKey;

public class RSAUtilTest  {

    @Test
    public void test1() throws Exception{
        System.out.println(RSAUtil.getDefaultPrivateKey().getPrivateExponent());
        System.out.println(RSAUtil.getDefaultPrivateKey().getPrivateExponent());
        System.out.println(RSAUtil.getRSAKeyPair()[0]);
        System.out.println(RadixUtil.binaryBytesToHexString(RSAUtil.getDefaultPrivateKey().getEncoded()));
        System.out.println( "-----------------------");
        System.out.println(RSAUtil.getDefaultPublicKey().getPublicExponent());
        System.out.println(RSAUtil.getRSAKeyPair()[1]);
    }

    @Test
    public void test2() {
        RSAPublicKey publicKey = RSAUtil.getRSAPublicKey("154471992999058139479994460025815654498183391593444870454838266974581244599191659985455957889064163942388409487313472074598227824609910604156744751985833898809065078785899074110993629452358669379496163284362583792866500058660069050752020922895749548342185553141417346777273482310707415185758164008066298773949", "65537");
        System.out.println(publicKey.getPublicExponent());
        System.out.println(RSAUtil.getRSAPublicKey("32581949", "6537").getPublicExponent());
    }
}
