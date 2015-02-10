## Additional configuration in OMX module, include
# USE_H263_DECODER for OMX Media Component H263 Decoder Library, default is DISABLE (0)
# USE_VC1_DECODER for OMX Media Component VC-1 Decoder Library, default is DISABLE (0)
# USE_MPEG4_DECODER for OMX Media Component MPEG-4 Decoder Library, default is DISABLE (0)
# USE_MPEG2_DECODER for VCP3 Driver Adapted for Linux MPEG-2 Decoder Library, default is DISABLE (0)
# USE_DIVX_DECODER for DivX Decoder Library for Linux, default is DISABLE (0)
USE_H263_DECODER = "${@'1' if 'h263dec_lib' in '${DISTRO_FEATURES}' else '0'}"
USE_VC1_DECODER = "${@'1' if 'vc1dec_lib' in '${DISTRO_FEATURES}' else '0'}"
USE_MPEG2_DECODER = "${@'1' if 'mpeg2dec_lib' in '${DISTRO_FEATURES}' else '0'}"
USE_MPEG4_DECODER = "${@'1' if 'mpeg4dec_lib' in '${DISTRO_FEATURES}' else '0'}"
USE_DIVX_DECODER = "${@'1' if 'divxdec_lib' in '${DISTRO_FEATURES}' else '0'}"

## Config to build H264AVC encoder
USE_H264AVC_ENCODER = "${@'1' if 'h264avcenc_lib' in '${DISTRO_FEATURES}' else '0'}"

## Config for audio decoders
#USE_AACP2_DECODER for ARM 5.1ch aacPlus V2 Decoder for Linux
#USE_MP3_DECODER for ARM MP3 Decoder for Linux
#USE_WMA_DECODER for ARM WMA Decoder for Linux
#USE_DDD_DECODER for ARM 5.1ch Dolby Digital Decoder for Linux
#USE_ALAC_DECODER for ARM ALAC Decoder for Linux
#USE_FLAC_DECODER for ARM FLAC Decoder for Linux
USE_AACP2_DECODER = "${@'1' if 'aacp2dec_lib' in '${DISTRO_FEATURES}' else '0'}"
USE_MP3_DECODER = "${@'1' if 'mp3dec_lib' in '${DISTRO_FEATURES}' else '0'}"
USE_WMA_DECODER = "${@'1' if 'wmadec_lib' in '${DISTRO_FEATURES}' else '0'}"
USE_DDD_DECODER = "${@'1' if 'ddddec_lib' in '${DISTRO_FEATURES}' else '0'}"
USE_ALAC_DECODER = "${@'1' if 'alacdec_lib' in '${DISTRO_FEATURES}' else '0'}"
USE_FLAC_DECODER = "${@'1' if 'flacdec_lib' in '${DISTRO_FEATURES}' else '0'}"

## Config for audio encoders
# USE_AAC_ENCODER for ARM AAC Encoder for Linux
USE_AAC_ENCODER = "${@'1' if 'aacenc_lib' in '${DISTRO_FEATURES}' else '0'}"

# Config for audio middlewares
# USE_ARMAACP2_MDW_DECODER for ARM 5.1ch aacPlus V2 Decode Middleware for Linux
# USE_MP3_MDW_DECODER for ARM MP3 Decode Middleware for Linux
# USE_WMA_MDW_DECODER for ARM WMA Decode Middleware for Linux
# USE_DDD_MDW_DECODER for ARM 5.1ch Dolby Digital Decode Middleware for Linux
# USE_ALAC_MDW_DECODER for ARM ALAC Decode Middleware for Linux
# USE_FLAC_MDW_DECODER for ARM FLAC Decode Middleware for Linux
USE_ARMAACP2_MDW_DECODER = "${@'1' if 'armaccp2dec_mdw' in '${DISTRO_FEATURES}' else '0'}"
USE_MP3_MDW_DECODER = "${@'1' if 'mp3dec_mdw' in '${DISTRO_FEATURES}' else '0'}"
USE_WMA_MDW_DECODER = "${@'1' if 'wmadec_mdw' in '${DISTRO_FEATURES}' else '0'}"
USE_DDD_MDW_DECODER = "${@'1' if 'ddddec_mdw' in '${DISTRO_FEATURES}' else '0'}"
USE_ALAC_MDW_DECODER = "${@'1' if 'alacdec_mdw' in '${DISTRO_FEATURES}' else '0'}"
USE_FLAC_MDW_DECODER = "${@'1' if 'flacdec_mdw' in '${DISTRO_FEATURES}' else '0'}"
##
# USE_AAC_MDW_ENCODER for ARM AAC Encode Middleware for Linux
USE_AAC_MDW_ENCODER = "${@'1' if 'aacenc_mdw' in '${DISTRO_FEATURES}' else '0'}"

#############################################################
##### Below part is auto dependent functions,
##### user don't touch below part
#############################################################
# Config for audio common omx
AUDIO_DECODER_ARRAY = "${USE_AACP2_DECODER}"
AUDIO_DECODER_ARRAY += "${USE_MP3_DECODER}"
AUDIO_DECODER_ARRAY += "${USE_WMA_DECODER}"
AUDIO_DECODER_ARRAY += "${USE_DDD_DECODER}"
AUDIO_DECODER_ARRAY += "${USE_ALAC_DECODER}"
AUDIO_DECODER_ARRAY += "${USE_FLAC_DECODER}"
AUDIO_ENCODER_ARRAY = "${USE_AAC_ENCODER}"
USE_AUDIO_COMMON = "${@'1' if '1' in '${AUDIO_ENCODER_ARRAY}' or '1' in '${AUDIO_DECODER_ARRAY}' else '0' }"

# Auto config for video common encoder. It will enable when there is at least 1 encoder is enabled.
# Please add new encoder config into list when have new encoder (e.g. as RESERVE_ENCODER_CONF)
VIDEO_ENCODER_ARRAY = "${USE_H264AVC_ENCODER}"
#VIDEO_ENCODER_ARRAY += "${RESERVE_ENCODER_CONF}"
USE_VIDEO_COMMON_ENCODER = '${@base_contains("VIDEO_ENCODER_ARRAY", "1", "1", "0", d)}'

# Audio codec config
ARMAACP2_MDW_DECODER = '${@base_conditional("USE_AACP2_DECODER", "1", "1", "${USE_ARMAACP2_MDW_DECODER}", d)}'
MP3_MDW_DECODER = '${@base_conditional("USE_MP3_DECODER", "1", "1", "${USE_MP3_MDW_DECODER}", d)}'
WMA_MDW_DECODER = '${@base_conditional("USE_WMA_DECODER", "1", "1", "${USE_WMA_MDW_DECODER}", d)}'
DDD_MDW_DECODER = '${@base_conditional("USE_DDD_DECODER", "1", "1", "${USE_DDD_MDW_DECODER}", d)}'
ALAC_MDW_DECODER = '${@base_conditional("USE_ALAC_DECODER", "1", "1", "${USE_ALAC_MDW_DECODER}", d)}'
FLAC_MDW_DECODER = '${@base_conditional("USE_FLAC_DECODER", "1", "1", "${USE_FLAC_MDW_DECODER}", d)}'
AAC_MDW_ENCODER = '${@base_conditional("USE_AAC_ENCODER", "1", "1", "${USE_AAC_MDW_ENCODER}", d)}'
