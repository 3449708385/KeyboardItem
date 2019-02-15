package com.yishenxiao.usermanager.service;

import java.util.Map;

public interface LoginService {

	Map<String, Object> userLogin(String invitationCode, String noNativeCodePhone, 
			String nativecode, String deviceIdentificationCode, int invitationCodeISBoolean);

}
