# USE_MULTIMEDIA for Renesas Multimedia package
USE_MULTIMEDIA = "${@'1' if 'multimedia' in '${MACHINE_FEATURES}' else '0'}"
USE_MULTIMEDIA_TP = "${@'1' if 'mm-test' in '${DISTRO_FEATURES}' else '0'}"

## Additional configuration in OMX module, include
# USE_H264D_OMX for OMX Media Component H264 OMX Library
USE_H264D_OMX = "${@'1' if 'h264dec_lib' in '${DISTRO_FEATURES}' else '0'}"

# USE_H265D_OMX for OMX Media Component H265 OMX Library
USE_H265D_OMX = "${@'1' if 'h265dec_lib' in '${DISTRO_FEATURES}' else '0'}"

# USE_MPEG2_OMX for OMX Media Component MPEG2 OMX Library
USE_MPEG2D_OMX = "${@'1' if 'mpeg2dec_lib' in '${DISTRO_FEATURES}' else '0'}"

# USE_AACLCD_OMX for OMX Media Component AACLC OMX Library
USE_AACLCD_OMX = "${@'1' if 'aaclcdec_lib' in '${DISTRO_FEATURES}' else '0'}"

# USE_AACLC_MDW for AACLC MDW Library
USE_AACLC_MDW =  "${@'1' if 'aaclcdec_mdw' in '${DISTRO_FEATURES}' else '0'}"

# OMX Video Decoder Flag list
LIST_OMX_VIDEO_DEC_FLAG = " \
    ${USE_H264D_OMX} \
    ${USE_H265D_OMX} \
    ${USE_MPEG2D_OMX} \
"

# OMX Video Encoder Flag list
LIST_OMX_VIDEO_ENC_FLAG = " \
"

# OMX Audio Flag list (except MDW)
LIST_OMX_AUDIO_FLAG = " \
    ${USE_AACLCD_OMX} \
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
