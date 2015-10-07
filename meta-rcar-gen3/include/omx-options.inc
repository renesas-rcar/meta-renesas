## Additional configuration in OMX module, include
# USE_H264D_OMX for OMX Media Component H264 OMX Library, default is DISABLE (0)
USE_H264D_OMX = "${@'1' if 'h264dec_lib' in '${DISTRO_FEATURES}' else '0'}"

# USE_AACLCD_OMX for OMX Media Component AACLC OMX Library, default is DISABLE (0)
USE_AACLCD_OMX = "${@'1' if 'aaclcdec_lib' in '${DISTRO_FEATURES}' else '0'}"


def enable_omx_mc_audio_common_libs(d):
    omx_audio_dec_libs_list = ['USE_AACLCD_OMX']
    for libs in omx_audio_dec_libs_list:
        audio_omx_enable = d.getVar(libs, True)
        if audio_omx_enable == "1":
            return "1"

    return 0

def enable_audio_middleware(d, audio):
    omx_audio_dec_libs_dict = {'aaclcdec_mdw':'USE_AACLCD_OMX'}
    use_aaclcdec_mdw = bb.utils.contains("DISTRO_FEATURES", audio, "1", "", d)
    use_omx_mc_libs = d.getVar(omx_audio_dec_libs_dict[audio],True)
    if use_aaclcdec_mdw == "1" or use_omx_mc_libs == "1":
        return "1"

    return 0

# USE_AACLC MDW for AACLC MDW Library, default is DISABLE (0)
USE_AACLC_MDW = "${@enable_audio_middleware(d, "aaclcdec_mdw")}"

# USE_AUDIO_OMX for AACLC MDW Library, default is DISABLE (0)
USE_AUDIO_OMX = "${@enable_omx_mc_audio_common_libs(d)}"
