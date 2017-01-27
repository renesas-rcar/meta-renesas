## Additional configuration in OMX module, include
# USE_H263D_OMX for OMX Media Component H263 OMX Library
USE_H263D_OMX = "${@'1' if 'h263dec_lib' in '${DISTRO_FEATURES}' else '0'}"

# USE_H264D_OMX for OMX Media Component H264 OMX Library
USE_H264D_OMX = "${@'1' if 'h264dec_lib' in '${DISTRO_FEATURES}' else '0'}"

# USE_H264E_OMX for OMX Media Component H264 Encoder Library
USE_H264E_OMX = "${@'1' if 'h264enc_lib' in '${DISTRO_FEATURES}' else '0'}"

# USE_H265D_OMX for OMX Media Component H265 OMX Library
USE_H265D_OMX = "${@'1' if 'h265dec_lib' in '${DISTRO_FEATURES}' else '0'}"

# USE_MPEG2_OMX for OMX Media Component MPEG2 OMX Library
USE_MPEG2D_OMX = "${@'1' if 'mpeg2dec_lib' in '${DISTRO_FEATURES}' else '0'}"

# USE_MPEG4D_OMX for OMX Media Component MPEG4 Decoder Library
USE_MPEG4D_OMX = "${@'1' if 'mpeg4dec_lib' in '${DISTRO_FEATURES}' else '0'}"

# USE_VC1D_OMX for OMX Media Component VC-1 Decoder Library
USE_VC1D_OMX = "${@'1' if 'vc1dec_lib' in '${DISTRO_FEATURES}' else '0'}"

# USE_DIVXD_OMX for OMX Media Component DivX Decoder Library
USE_DIVXD_OMX = "${@'1' if 'divxdec_lib' in '${DISTRO_FEATURES}' else '0'}"

# USE_RVD_OMX for OMX Media Component RealVideo Decoder Library
USE_RVD_OMX = "${@'1' if 'rvdec_lib' in '${DISTRO_FEATURES}' else '0'}"

# USE_AACLCD_OMX for OMX Media Component AACLC OMX Library
USE_AACLCD_OMX = "${@'1' if 'aaclcdec_lib' in '${DISTRO_FEATURES}' else '0'}"

# USE_AACPV2D_OMX for OMX Media Component aacPlus V2 OMX Library
USE_AACPV2D_OMX = "${@'1' if 'aacpv2dec_lib' in '${DISTRO_FEATURES}' else '0'}"

# USE_MP3D_OMX for OMX Media Component MP3 OMX Library
USE_MP3D_OMX = "${@'1' if 'mp3dec_lib' in '${DISTRO_FEATURES}' else '0'}"

# USE_WMAD_OMX for OMX Media Component WMA OMX Library
USE_WMAD_OMX = "${@'1' if 'wmadec_lib' in '${DISTRO_FEATURES}' else '0'}"

# USE_ALACD_OMX for OMX Media Component ALAC decoder Library
USE_ALACD_OMX = "${@'1' if 'alacdec_lib' in '${DISTRO_FEATURES}' else '0'}"

# USE_FLACD_OMX for OMX Media Component FLAC decoder Library
USE_FLACD_OMX = "${@'1' if 'flacdec_lib' in '${DISTRO_FEATURES}' else '0'}"

# USE_DDD_OMX for OMX Media Component Dolby(R) Digital decoder Library
USE_DDD_OMX = "${@'1' if 'dddec_lib' in '${DISTRO_FEATURES}' else '0'}"

# USE_AACLCE_OMX for OMX Media Component AAC-LC encoder Library
USE_AACLCE_OMX = "${@'1' if 'aaclcenc_lib' in '${DISTRO_FEATURES}' else '0'}"

# USE_AACLC_MDW for AACLC MDW Library
USE_AACLC_MDW =  "${@'1' if 'aaclcdec_mdw' in '${DISTRO_FEATURES}' else '0'}"

# USE_AACPV2_MDW for aacPlus V2 MDW Library
USE_AACPV2_MDW =  "${@'1' if 'aacpv2dec_mdw' in '${DISTRO_FEATURES}' else '0'}"

# USE_MP3_MDW for MP3 MDW Library
USE_MP3_MDW =  "${@'1' if 'mp3dec_mdw' in '${DISTRO_FEATURES}' else '0'}"

# USE_WMA_MDW for WMA MDW Library
USE_WMA_MDW =  "${@'1' if 'wmadec_mdw' in '${DISTRO_FEATURES}' else '0'}"

# USE_DD_MDW for Dolby(R) Digital MDW Library
USE_DD_MDW =  "${@'1' if 'dddec_mdw' in '${DISTRO_FEATURES}' else '0'}"

# USE_AACLCE_OMX for AAC-LC encoder middleware Library
USE_AACLCE_MDW = "${@'1' if 'aaclcenc_mdw' in '${DISTRO_FEATURES}' else '0'}"

# OMX Video Decoder Flag list
LIST_OMX_VIDEO_DEC_FLAG = " \
    ${USE_H263D_OMX} \
    ${USE_H264D_OMX} \
    ${USE_H265D_OMX} \
    ${USE_MPEG2D_OMX} \
    ${USE_MPEG4D_OMX} \
    ${USE_VC1D_OMX} \
    ${USE_DIVXD_OMX} \
    ${USE_RVD_OMX} \
"

# OMX Video Encoder Flag list
LIST_OMX_VIDEO_ENC_FLAG = " \
    ${USE_H264E_OMX} \
"

# OMX Audio Flag list (except MDW)
LIST_OMX_AUDIO_FLAG = " \
    ${USE_AACLCD_OMX} \
    ${USE_AACPV2D_OMX} \
    ${USE_MP3D_OMX} \
    ${USE_WMAD_OMX} \
    ${USE_AACLCE_OMX} \
    ${USE_ALACD_OMX} \
    ${USE_FLACD_OMX} \
    ${USE_DDD_OMX} \
"

# Audio M/W Flag list
LIST_AUDIO_MDW_FLAG = " \
    ${USE_AACLC_MDW} \
    ${USE_AACPV2_MDW} \
    ${USE_MP3_MDW} \
    ${USE_WMA_MDW} \
    ${USE_DD_MDW} \
    ${USE_AACLCE_MDW} \
"

# USE_OMX_VIDEO_DEC for Video Decoder Common Libs
USE_VIDEO_DEC = "${@'1' if '1' in '${LIST_OMX_VIDEO_DEC_FLAG}' else '0'}"

# USE_OMX_VIDEO_DEC for Video Encoder Common Libs
USE_VIDEO_ENC = "${@'1' if '1' in '${LIST_OMX_VIDEO_ENC_FLAG}' else '0'}"

# USE_VIDEO_OMX for Enable/Disable OMX Video
USE_VIDEO_OMX = "${@'1' if '1' in '${USE_VIDEO_DEC} ${USE_VIDEO_ENC}' else '0'}"

# USE_AUDIO_OMX for Audio Common Libs
USE_AUDIO_OMX = "${@'1' if '1' in '${LIST_OMX_AUDIO_FLAG}' else '0'}"

# USE_OMX_COMMON for OMX Common Libs
USE_OMX_COMMON = "${@'1' if '1' in '${USE_VIDEO_OMX} ${USE_AUDIO_OMX}' else '0'}"

# USE_OMX_USER_MODULE for OMX User Module
# Note) List of USE_OMX_COMMON = {USE_VIDEO_OMX} + {USE_AUDIO_OMX}
USE_OMX_USER_MODULE = "${@'1' if '1' in '${USE_OMX_COMMON} ${LIST_AUDIO_MDW_FLAG}' else '0'}"

USE_OMX_EVA_PKG = "${@'1' if 'use_eva_pkg' in '${DISTRO_FEATURES}' else '0'}"
