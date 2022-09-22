import JSEncrypt from 'jsencrypt';

export class RsaService{

  //rsa加密
  static rsaEncrypt(obj){
    if(obj == null){
      return ;
    }
    const jse = new JSEncrypt();
    jse.setPublicKey(`-----BEGIN PUBLIC KEY-----
        MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEAwsUd8ZmZmPNxFyCyNvP3
        HW2uNVK3kq+A6skaxmDRosFMAJ6t1H/OijGq43vs16J2qxg2WLtquTOuqe5ourzM
        h7ZXaobLfKwrWiOqh7YSXbld1xHVg9mmARZ7R7e/37NDDpUW9pUHTARd0E9+OQ6P
        IzhdNmUxHHTjOWPuhReHSYnoLYIx2c+JtRRC2Xcui0S7jFgHSAjxygqYpN+TkBMf
        95KSYiTRbrNKK6AdquFv2jztvRWrivG2YwQRjy0SFStcKaH977MABhGs3YciPwWe
        syN9r1/Q9BYLYxcSW7CkVkVNvhCa2XUht+MJTyYQCXp0+/VFFEigi6KwD22jk12s
        A88ylCgXE2mFTcjY6zXBrwbT6+DogVmOWJr70oNqcsvWeRxMaQSmlPXHvNee7ucS
        FmFsGagXOwgpWS08Op1vv8Ge3dEtSMVW9RKTkevUIzdmmkUy7qIoIa5T5Q1799y8
        Nr2RYAJaE1b6jpQd8xhseu3MYnOA16zmjFfdmAlqpNSt+usDepUk6INk0/WGXJ69
        QyUCdMR0tNYeRT3URCVdJd/S7gsAWtg/xYsh8N0ForAwGpuNoVgOkII0XLS9rxMb
        3dojF+5k9YOvmJ52+TbIzCJI0liKcMIl31ZWfqxxm3CASqgvsSzGkcpeZVictnL/
        vZMPg5Rq7jv+bS/mJ4RdVn0CAwEAAQ==
        -----END PUBLIC KEY-----`);
    const jsonObj = JSON.stringify(obj);
    const encrypted = jse.encrypt(jsonObj);
    return encrypted;
  }
}

