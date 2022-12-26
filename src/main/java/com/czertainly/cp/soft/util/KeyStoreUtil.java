package com.czertainly.cp.soft.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.cert.CertificateException;

public class KeyStoreUtil {

    public static byte[] createNewKeystore(String type, String code) {
        if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null) {
            Security.addProvider(new BouncyCastleProvider());
        }

        try {
            KeyStore ks = KeyStore.getInstance(type);
            char[] password = code.toCharArray();
            ks.load(null, password);

            // store the keystore
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ks.store(baos, password);

            return baos.toByteArray();
        } catch (CertificateException e) {
            throw new IllegalStateException("Certificate exception for KeyStore", e);
        } catch (KeyStoreException e) {
            throw new IllegalStateException("Invalid KeyStore ", e);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot create new KeyStore", e);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Invalid algorithm for KeyStore ", e);
        }
    }


}
