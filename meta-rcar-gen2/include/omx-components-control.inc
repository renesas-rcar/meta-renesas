## Additional configuration in OMX module, include
# H263_DECODER_CONF for OMX Media Component H263 Decoder Library, default is DISABLE (0)
# VC1_DECODER_CONF for OMX Media Component VC-1 Decoder Library, default is DISABLE (0)
# MPEG4_DECODER_CONF for OMX Media Component MPEG-4 Decoder Library, default is DISABLE (0)
# MPEG2_DECODER_CONF for VCP3 Driver Adapted for Linux MPEG-2 Decoder Library, default is DISABLE (0)
# DIVX_DECODER_CONF for DivX Decoder Library for Linux, default is DISABLE (0)
H263_DECODER_CONF = "${@'1' if 'h263dec' in '${DISTRO_FEATURES}' else '0'}"
VC1_DECODER_CONF = "${@'1' if 'vc1dec' in '${DISTRO_FEATURES}' else '0'}"
MPEG2_DECODER_CONF = "${@'1' if 'mpeg2dec' in '${DISTRO_FEATURES}' else '0'}"
MPEG4_DECODER_CONF = "${@'1' if 'mpeg4dec' in '${DISTRO_FEATURES}' else '0'}"
DIVX_DECODER_CONF = "${@'1' if 'divxdec' in '${DISTRO_FEATURES}' else '0'}"

## Config to build H264AVC encoder
H264AVC_ENCODER_CONF = "${@'1' if 'h264avcenc' in '${DISTRO_FEATURES}' else '0'}"

## Config for audio decoders
#AACP2_DECODER_CONF for ARM 5.1ch aacPlus V2 Decoder for Linux
#MP3_DECODER_CONF for ARM MP3 Decoder for Linux
#WMA_DECODER_CONF for ARM WMA Decoder for Linux
#DDD_DECODER_CONF for ARM 5.1ch Dolby Digital Decoder for Linux
#ALAC_DECODER_CONF for ARM ALAC Decoder for Linux
#FLAC_DECODER_CONF for ARM FLAC Decoder for Linux
AACP2_DECODER_CONF = "${@'1' if 'aacp2dec' in '${DISTRO_FEATURES}' else '0'}"
MP3_DECODER_CONF = "${@'1' if 'mp3dec' in '${DISTRO_FEATURES}' else '0'}"
WMA_DECODER_CONF = "${@'1' if 'wmadec' in '${DISTRO_FEATURES}' else '0'}"
DDD_DECODER_CONF = "${@'1' if 'ddddec' in '${DISTRO_FEATURES}' else '0'}"
ALAC_DECODER_CONF = "${@'1' if 'alacdec' in '${DISTRO_FEATURES}' else '0'}"
FLAC_DECODER_CONF = "${@'1' if 'flacdec' in '${DISTRO_FEATURES}' else '0'}"

## Config for audio encoders
# AAC_ENCODER_CONF for ARM AAC Encoder for Linux
AAC_ENCODER_CONF = "${@'1' if 'aacenc' in '${DISTRO_FEATURES}' else '0'}"

# Config for audio middlewares
# ARMAACP2_MDW_DECODER_CONF for ARM 5.1ch aacPlus V2 Decode Middleware for Linux
# MP3_MDW_DECODER_CONF for ARM MP3 Decode Middleware for Linux
# WMA_MDW_DECODER_CONF for ARM WMA Decode Middleware for Linux
# DDD_MDW_DECODER_CONF for ARM 5.1ch Dolby Digital Decode Middleware for Linux
# ALAC_MDW_DECODER_CONF for ARM ALAC Decode Middleware for Linux
# FLAC_MDW_DECODER_CONF for ARM FLAC Decode Middleware for Linux
ARMAACP2_MDW_DECODER_CONF = "${@'1' if 'armaccp2dec_mdw' in '${DISTRO_FEATURES}' else '0'}"
MP3_MDW_DECODER_CONF = "${@'1' if 'mp3dec_mdw' in '${DISTRO_FEATURES}' else '0'}"
WMA_MDW_DECODER_CONF = "${@'1' if 'wmadec_mdw' in '${DISTRO_FEATURES}' else '0'}"
DDD_MDW_DECODER_CONF = "${@'1' if 'ddddec_mdw' in '${DISTRO_FEATURES}' else '0'}"
ALAC_MDW_DECODER_CONF = "${@'1' if 'alacdec_mdw' in '${DISTRO_FEATURES}' else '0'}"
FLAC_MDW_DECODER_CONF = "${@'1' if 'flacdec_mdw' in '${DISTRO_FEATURES}' else '0'}"
##
# AAC_MDW_ENCODER_CONF for ARM AAC Encode Middleware for Linux
AAC_MDW_ENCODER_CONF = "${@'1' if 'aacenc_mdw' in '${DISTRO_FEATURES}' else '0'}"

#############################################################
##### Below part is auto dependent functions,
##### user don't touch below part
#############################################################
# Config for audio common omx
AUDIO_DECODER_ARRAY = "${AACP2_DECODER_CONF}"
AUDIO_DECODER_ARRAY += "${MP3_DECODER_CONF}"
AUDIO_DECODER_ARRAY += "${WMA_DECODER_CONF}"
AUDIO_DECODER_ARRAY += "${DDD_DECODER_CONF}"
AUDIO_DECODER_ARRAY += "${ALAC_DECODER_CONF}"
AUDIO_DECODER_ARRAY += "${FLAC_DECODER_CONF}"
AUDIO_ENCODER_ARRAY = "${AAC_ENCODER_CONF}"
AUDIO_COMMON_CONF = "${@'1' if '1' in '${AUDIO_ENCODER_ARRAY}' or '1' in '${AUDIO_DECODER_ARRAY}' else '0' }"

# Auto config for video common encoder. It will enable when there is at least 1 encoder is enabled.
# Please add new encoder config into list when have new encoder (e.g. as RESERVE_ENCODER_CONF)
VIDEO_ENCODER_ARRAY = "${H264AVC_ENCODER_CONF}"
#VIDEO_ENCODER_ARRAY += "${RESERVE_ENCODER_CONF}"
VIDEO_COMMON_ENCODER_CONF = '${@base_contains("VIDEO_ENCODER_ARRAY", "1", "1", "0", d)}'

# Audio codec config
ARMAACP2_MDW_DECODER = '${@base_conditional("AACP2_DECODER_CONF", "1", "1", "${ARMAACP2_MDW_DECODER_CONF}", d)}'
MP3_MDW_DECODER = '${@base_conditional("MP3_DECODER_CONF", "1", "1", "${MP3_MDW_DECODER_CONF}", d)}'
WMA_MDW_DECODER = '${@base_conditional("WMA_DECODER_CONF", "1", "1", "${WMA_MDW_DECODER_CONF}", d)}'
DDD_MDW_DECODER = '${@base_conditional("DDD_DECODER_CONF", "1", "1", "${DDD_MDW_DECODER_CONF}", d)}'
ALAC_MDW_DECODER = '${@base_conditional("ALAC_DECODER_CONF", "1", "1", "${ALAC_MDW_DECODER_CONF}", d)}'
FLAC_MDW_DECODER = '${@base_conditional("FLAC_DECODER_CONF", "1", "1", "${FLAC_MDW_DECODER_CONF}", d)}'
AAC_MDW_ENCODER = '${@base_conditional("AAC_ENCODER_CONF", "1", "1", "${AAC_MDW_ENCODER_CONF}", d)}'
