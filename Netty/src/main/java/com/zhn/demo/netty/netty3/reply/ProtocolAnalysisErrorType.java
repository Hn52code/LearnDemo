package com.zhn.demo.netty.netty3.reply;

public enum ProtocolAnalysisErrorType {

    ERROR_OVER_LIMIT_NO_SIGN,
    ERROR_LEN,
    ERROR_ID,
    ERROR_CMD,
    ERROR_STA,
    ERROR_DATA_NO_ENOUGH_LEN,
    ERROR_DATA_REGISTER_TYPE,
    ERROR_DATA_WB_TYPE,
    ERROR_DATA_WB_PROBE,
    ERROR_DATA_WB_AREA,
    ERROR_DATA_PARTS,
    ERROR_CHECK;

}
