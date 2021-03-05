package common.enumeration.fileTypeEnum;

public enum FileType {
    // 未知
    UNKNOWN,
    // 压缩文件
    ZIP, RAR, _7Z, TAR, GZ, BZ2, TAR_GZ, TAR_BZ2,
    //文本文件
    DOC, RTF, WRI, TXT, PDF,
    // 位图文件
    BMP, PNG, JPG, JPEG,
    // 矢量图文件
    SVG,
    // 影音文件
    AVI, MP4, MP3, AAR, OGG, WAV, WAVE, RAM, WMA, MOV,
    //系统文件
    SYS, INT, DLL, ADT,
    //可执行文件
    EXE, COM,
    //批处理文件
    BAT;

}
