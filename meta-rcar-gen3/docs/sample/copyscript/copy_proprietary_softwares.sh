#!/bin/sh

# Copyright (C) Renesas Electronics Corporation 2015 All rights reserved.

usage()
{
cat << EOF
    usage: `basename $0` [-f] [-d] source-directory
    -f: fource copy. ignore md5check
    -d: debug mode

    Ex)
    `basename $0` -f my_package_dir
EOF
}

##### MD5 list #####
. `dirname $0`/md5list.txt

##### Library List #####
# Audio Library
# Please add omx audio library to "_audio_list"
# Don't use space in xxx_name.
# audio_list="<software_name>,<package_name>,<copy_file_name> \
#             <software_name>,<package_name>,<copy_file_name> \
#             <software_name>,<package_name>,<copy_file_name>"
_audio_list="AAC-LC_decoder_lib,RTM0AC0000XAAACD30SL40C,RTM0AC0000XAAACD30SL40C.tar.gz \
             aacPlusV2_decoder_lib,RTM0AC0000XAAAPD30SL40C,RTM0AC0000XAAAPD30SL40C.tar.gz \
             MP3_decoder_lib,RTM0AC0000XAMP3D30SL40C,RTM0AC0000XAMP3D30SL40C.tar.gz \
             WMA_decoder_lib,RTM0AC0000XAWMAD30SL40C,RTM0AC0000XAWMAD30SL40C.tar.gz \
             AAC-LC_encoder_lib,RTM0AC0000XAAACE30SL40C,RTM0AC0000XAAACE30SL40C.tar.gz \
             ALAC_decoder_lib,RTM0AC0000XAALAD30SL40C,RTM0AC0000XAALAD30SL40C.tar.gz \
             FLAC_decoder_lib,RTM0AC0000XAFLAD30SL40C,RTM0AC0000XAFLAD30SL40C.tar.gz \
             Dolby_decoder_lib,RTM0AC0000XADD5D30SL40C,RTM0AC0000XADD5D30SL40C.tar.gz"

# Audio M/W Library
# Please add omx audio library to "_audio_mw_list"
# Don't use space in xxx_name.
# audio_mw_list="<software_name>,<package_name>,<copy_file_name> \
#                <software_name>,<package_name>,<copy_file_name> \
#                <software_name>,<package_name>,<copy_file_name>"
_audio_mw_list="AAC-LC_decoder_M/W,RTM0AC0000ADAACMZ1SL40C,RTM0AC0000ADAACMZ1SL40C.tar.gz \
                aacPlusV2_decoder_M/W,RTM0AC0000ADAAPMZ1SL40C,RTM0AC0000ADAAPMZ1SL40C.tar.gz \
                MP3_decoder_M/W,RTM0AC0000ADMP3MZ1SL40C,RTM0AC0000ADMP3MZ1SL40C.tar.gz \
                WMA_decoder_M/W,RTM0AC0000ADWMAMZ1SL40C,RTM0AC0000ADWMAMZ1SL40C.tar.gz \
                AAC-LC_encoder_M/W,RTM0AC0000AEAACMZ1SL40C,RTM0AC0000AEAACMZ1SL40C.tar.gz \
                DDD_decoder_M/W,RTM0AC0000ADDD5MZ1SL40C,RTM0AC0000ADDD5MZ1SL40C.tar.gz"

# Video Decoder Library
# Please add omx video decoder library to "_video_dec_list"
# Don't use space in xxx_name.
# video_dec_list="<software_name>,<package_name>,<copy_file_name> \
#                 <software_name>,<package_name>,<copy_file_name> \
#                 <software_name>,<package_name>,<copy_file_name>"
_video_dec_list="H264_decoder,RTM0AC0000XV264D30SL40C,RTM0AC0000XV264D30SL40C.tar.bz2 \
                 H265_decoder,RTM0AC0000XV265D30SL40C,RTM0AC0000XV265D30SL40C.tar.bz2 \
                 MPEG2_decoder,RTM0AC0000XVM2VD30SL40C,RTM0AC0000XVM2VD30SL40C.tar.bz2 \
                 MPEG4_decoder,RTM0AC0000XVM4VD30SL40C,RTM0AC0000XVM4VD30SL40C.tar.bz2 \
                 VC1_decoder,RTM0AC0000XVVC1D30SL40C,RTM0AC0000XVVC1D30SL40C.tar.bz2 \
                 DivX_decoder,RTM0AC0000XVDVXD30SL40C,RTM0AC0000XVDVXD30SL40C.tar.bz2 \
                 RealVideo_decoder,RTM0AC0000XVRLVD30SL40C,RTM0AC0000XVRLVD30SL40C.tar.bz2"

# Video Encoder Library
# Please add omx video encoder library to "_video_enc_list"
# Don't use space in xxx_name.
# video_enc_list="<software_name>,<package_name>,<copy_file_name> \
#                 <software_name>,<package_name>,<copy_file_name> \
#                 <software_name>,<package_name>,<copy_file_name>"
_video_enc_list="H264_encoder,RTM0AC0000XV264E30SL40C,RTM0AC0000XV264E30SL40C.tar.bz2"

# CMS Library
# Please add CMS library to "_cms_list"
# Don't use space in xxx_name.
# cms_list="<software_name>,<package_name>,<copy_file_name> \
#           <software_name>,<package_name>,<copy_file_name> \
#           <software_name>,<package_name>,<copy_file_name>"
_cms_list="bcm,RTM0AC0000JRCMBCV0SL40C,RTM0AC0000JRCMBCV0SL40C.tar.gz \
           blc,RTM0AC0000JRCMBLC0SL40C,RTM0AC0000JRCMBLC0SL40C.tar.gz \
           dgc,RTM0AC0000JRCMDGV0SL40C,RTM0AC0000JRCMDGV0SL40C.tar.gz"

# ADSP
# Please add ADSP to "_adsp_list"
# Don't use space in xxx_name.
# adsp_list="<software_name>,<package_name>,<copy_file_name> \
#            <software_name>,<package_name>,<copy_file_name> \
#            <software_name>,<package_name>,<copy_file_name>"
_adsp_list="adsp_fw,RCG3AHFWN0101ZDP,RCG3AHFWN0101ZDP.tar.gz \
            adsp_if,RCG3AHIFL4001ZDP,RCG3AHIFL4001ZDP.tar.gz \
            adsp_driver,RCG3AHPDL4001ZDO,RCG3AHPDL4001ZDO.tar.gz"

# GFX
# Please add GFX to "_gfx_list"
# Don't use space in xxx_name.
# gfx_list="<package_name(user)>,<copy_file_name(user)>,<package_name(kernel)>,<copy_file_name(kernel)> \
#           <package_name(user)>,<copy_file_name(user)>,<package_name(kernel)>,<copy_file_name(kernel)> \
#           <package_name(user)>,<copy_file_name(user)>,<package_name(kernel)>,<copy_file_name(kernel)>"
_gfx_list="RTM0RC7795GLTG0001SL40C,r8a7795_linux_gsx_binaries_gles3.tar.bz2,RCH3G001L4001ZDO,GSX_KM_H3.tar.bz2 \
           RTM0RC7796GLTG0001SL40C,r8a7796_linux_gsx_binaries_gles3.tar.bz2,RCM3G001L4001ZDO,GSX_KM_M3.tar.bz2"

# ICCOM
# Please add ICCOM to "_iccom_list"
# Don't use space in xxx_name.
# iccom_list="<software_name>,<package_name>,<copy_file_name>,<MD5_name> \
#             <software_name>,<package_name>,<copy_file_name>,<MD5_name> \
#             <software_name>,<package_name>,<copy_file_name>,<MD5_name>"
_iccom_list="iccom_mfis,RCG3ZLIDL4001ZNO,iccom-mfis.tar.bz2,RCG3ZLIDL4001ZNO1 \
             iccom_sample,RCG3ZLIDL4001ZNO,iccom-hwspinlock-sample.tar.bz2,RCG3ZLIDL4001ZNO2 \
             iccom_lib,RCG3ZLILL4001ZNO,libiccom.tar.bz2,RCG3ZLILL4001ZNO"

# Crypto Packgae list
# Please add crypto (zip) package name to "_crypto_pkg_list"
# Don't use space in xxx_name.
# crypto_pkg_list="<packgae name> <packgae name> <packgae name>"
_crypto_pkg_list="RTM0AC0000ADDD5MZ1SL40C"

##### static value
_MODE_ZIP=1
_MODE_TAR=2
_MODE_CRYPTO_ZIP=3
_GFX_KM_INST_DIR="../meta-rcar-gen3/recipes-kernel/kernel-module-gles/kernel-module-gles"
_GFX_UM_INST_DIR="../meta-rcar-gen3/recipes-graphics/gles-module/gles-user-module"
_UVCS_INST_DIR="../meta-rcar-gen3/recipes-kernel/kernel-module-uvcs/kernel-module-uvcs"
_OMX_UM_INST_DIR="../meta-rcar-gen3/recipes-multimedia/omx-module/omx-user-module"
_DTV_KM_INST_DIR="../meta-rcar-gen3/recipes-kernel/kernel-module-dtv/files"
_DTV_UM_INST_DIR="../meta-rcar-gen3/recipes-multimedia/dtv-module/dtv-user-module"
_DVD_UM_INST_DIR="../meta-rcar-gen3/recipes-multimedia/dvd-module/dvd-user-module"
_CMS_UM_INST_DIR="../meta-rcar-gen3/recipes-multimedia/cms-module/cms-user-module"
_ADSP_KM_INST_DIR="../meta-rcar-gen3/recipes-kernel/kernel-module-adsp/xtensa-hifi"
_ADSP_UM_INST_DIR="../meta-rcar-gen3/recipes-multimedia/adsp-module/files"
_ICCOM_KM_INST_DIR="../meta-rcar-gen3/recipes-kernel/kernel-module-iccom/files"
_ICCOM_UM_INST_DIR="../meta-rcar-gen3/recipes-connectivity/iccom-module/files"

##### common function

# $1: search file name
# $2: search directory
# return global variable
# _find_filename: find filename
# _extract_mode: _MODE_ZIP or _MODE_TAR
func_cmn_find_file()
{
#   echo "$1"
#   echo "$2"
    if [ -z "$1" ]; then
        func_error "ERROR: func_cmn_find_file: empty filename"
    fi

    if [ -z "$2" ]; then
        _search_dir=${_src_full}
    else
        _search_dir=$2
    fi

    # search zip file
#   zip_count=`find ${_search_dir} -maxdepth 1 -name "$1*.zip" | wc -l`
    zip_count=`ls ${_search_dir}/$1*.zip 2>/dev/null | wc -l`

    # search tar file
#   tar_count=`find ${_search_dir} -maxdepth 1 -name "$1*.tar.*" | wc -l`
    tar_count=`ls ${_search_dir}/$1*.tar.* 2>/dev/null | wc -l`

    # duplicate file check
    if [ 1 -lt `expr $zip_count + $tar_count` ]; then
        echo "file1_zip       = $zip_count"
        echo "file1_tar       = $tar_count"
        func_error "ERROR: $1: too many files"
    fi

    crypto_zip_count=0
    for i in ${_crypto_pkg_list}
    do
        if [ $1 = $i ]; then
            crypto_zip_count=$zip_count
            zip_count=0
        fi
    done

    # set result
    if [ 1 = $zip_count ]; then
        _find_filename=$(ls ${_search_dir}/$1*.zip)
        _extract_mode=${_MODE_ZIP}
    elif [ 1 = $tar_count ]; then
        _find_filename=$(ls ${_search_dir}/$1*.tar.*)
        _extract_mode=${_MODE_TAR}
    elif [ 1 = $crypto_zip_count ]; then
        _find_filename=$(ls ${_search_dir}/$1*.zip)
        _extract_mode=${_MODE_CRYPTO_ZIP}
    else
        _find_filename=""
    fi
}

# $1: Mode
# $2: archive file name
func_cmn_extract_archive()
{
    case $1 in
        $_MODE_ZIP)
#           echo "Zip mode"
            unzip -oq $2
            ;;
        $_MODE_TAR)
#           echo "Tar mode"
            tar xf $2
            ;;
        $_MODE_CRYPTO_ZIP)
#           echo "Crypto Zip mode"
            unzip -oq $2
            top_dir=$(basename $2)
            top_dir=${top_dir%.*}
            cd ${top_dir}
            unzip -oq *.zip
            if [ $? -gt 0 ]; then
                func_error "ERROR: FAILED ZIP PASSWORD"
            fi
            cd ${TMPWORK}
            ;;
        *)
            func_error "ERROR: func_cmn_extract_archive: mode error."
            exit 1
            ;;
    esac
}

# $1: set target filename.
# $2: set MD5 expectation value.
func_cmn_md5_check()
{
    _md5_func_param_filename=$1
    _md5_func_param_expectation=$2
    if [ ! -e ${_md5_func_param_filename} ]; then
        func_error "func_cmn_md5_check  : ERROR ${_md5_func_param_filename} not found."
    fi

    _calc_md5=$(md5sum ${_md5_func_param_filename} | cut -d " " -f1)

    if [ -n "${_no_md5check}" ] || [ -z ${_md5_func_param_expectation} ]; then
        echo "Skip MD5        : `basename ${_md5_func_param_filename}`"
        return
    fi

    if [ -n "${_debug}" ]; then
        echo "MD5 target file = ${_md5_func_param_filename}"
        echo "calc_md5        = ${_calc_md5}"
        echo "expect_value    = ${_md5_func_param_expectation}"
    fi

    if [ ${_calc_md5} = ${_md5_func_param_expectation} ]; then
        echo "MD5 OK          : `basename ${_md5_func_param_filename}`"
    else
        echo "calc_md5        = ${_calc_md5}"
        echo "expect_value    = ${_md5_func_param_expectation}"
        func_error "MD5 ERROR       : ${_md5_func_param_filename}"
    fi
}

##### Error function
# $1: error message
func_error()
{
    echo "$1"
    # cleanup temp directory.
    func_clean_tempdir
    exit 1
}

##### cleanup temp directory
func_clean_tempdir()
{
    echo "cleanup temp directory"
    rm -rf ${TMPWORK}
}

##### Template function for Single package

# $1: package name
# $2: copy filename (md5 target)
# $3: expect MD5 value
# $4: search directory (full path)
# return
# _find_file_name
# _extract_top_dir_name
func_search_and_md5check()
{
    # search package file
    func_cmn_find_file $1 $4
    if [ -n "${_debug}" ]; then
        echo ""
        echo "FileName        = ${_find_filename}"
        echo "Mode            = ${_extract_mode}"
    fi

    if [ -z "${_find_filename}" ]; then
        return
    fi

    # extract
    func_cmn_extract_archive ${_extract_mode} "${_find_filename}"

    # MD5
    # Get directory name
    # {PATH}/Package_Version.tar.gz or XXXX.zip --> Package_Version
    top_dir=$(basename ${_find_filename})
    top_dir=${top_dir%.*}

    # call func_cmn_md5_check
    func_cmn_md5_check "${top_dir}/$1/Software/$2" "$3"

    # set return value
    _extract_top_dir_name=${top_dir}
}

##### Template function for Group package
# $1: group package name
# $2: single package name
# $3: copy filename (md5 target)
# $4: expect MD5 value
# $5: search directory (full path)
# return
# _find_file_name
# _extract_top_dir_name
#
# NOTE) This function support level1 packaging. "grp pkg in grp pkg" dose note support.
func_search_and_md5check_grp()
{
    # search group package @SRCDIR
    func_cmn_find_file $1 $5
    if [ -n "${_debug}" ]; then
        echo "search group package"
        echo "FileName        = ${_find_filename}"
        echo "Mode            = ${_extract_mode}"
        echo ""
    fi

    if [ -z "${_find_filename}" ]; then
        return
    fi

    # extract group package @TMPWORK
    func_cmn_extract_archive ${_extract_mode} "${_find_filename}"

    cd ${_find_filename}
    # check group pachage structure
    # <Package name>_<version>/Package_Info.txt
    top_dir=$(basename ${_find_filename})
    top_dir=${top_dir%.*}

    if [ ! -e ${top_dir}/Package_Info.txt ]; then
        echo "grp package        = $1"
        echo "single package     = $2"
        func_error "ERROR: Package_Info.txt not found in Group package."
    fi

    # search single package @TMPWORK/TOPDIR/<Group Package name>/
    func_cmn_find_file $2 "${TMPWORK}/${top_dir}/$1"
    if [ -n "${_debug}" ]; then
        echo "search single package"
        echo "FileName        = ${_find_filename}"
        echo "Mode            = ${_extract_mode}"
        echo ""
    fi

    if [ -z "${_find_filename}" ]; then
        return
    fi

    # mv <single package> TMPWORK/.
    mv ${_find_filename} ${TMPWORK}/.

    # delete group package
    rm -rf ${TMPWORK}/${top_dir}

    # call search and md5check @TMPWORK
    func_search_and_md5check $2 $3 $4 ${TMPWORK}
}

##### Package function

# For 3D graphics packages
func_gfx()
{
    echo ""
    echo "Copying for Graphic Packages"

    copy_flag=0

    for i in ${_gfx_list}
    do
        user_pkg_name=`echo $i | cut -d "," -f 1`
        user_copyfile_name=`echo $i | cut -d "," -f 2`
        user_md5_val=`eval echo '$_MD5_'${user_pkg_name}`
        kern_pkg_name=`echo $i | cut -d "," -f 3`
        kern_copyfile_name=`echo $i | cut -d "," -f 4`
        kern_md5_val=`eval echo '$_MD5_'${kern_pkg_name}`

        if [ -n "${_debug}" ]; then
            echo ""
            echo "user_pkg_name   = ${user_pkg_name}"
            echo "user_copyfile_name= ${user_copyfile_name}"
            echo "user_md5_val    = ${user_md5_val}"
            echo "kern_pkg_name   = ${kern_pkg_name}"
            echo "kern_copyfile_name= ${kern_copyfile_name}"
            echo "kern_md5_val    = ${kern_md5_val}"
        fi

        # user module (file1)
        func_search_and_md5check "${user_pkg_name}" "${user_copyfile_name}" "${user_md5_val}" "${_src_full}"

        if [ -z "${_find_filename}" ]; then
            echo "${user_pkg_name} not found!"
            continue
        fi

        file1_top_dir=${_extract_top_dir_name}
        echo "file1 top       : ${file1_top_dir}"

        # kernel module (file2)
        func_search_and_md5check "${kern_pkg_name}" "${kern_copyfile_name}" "${kern_md5_val}" "${_src_full}"

        # file1 exist, but file2 not exist
        if [ -z "${_find_filename}" ]; then
            func_error "ERROR: func_gfx: package file for Graphic is incomplete."
        fi

        file2_top_dir=${_extract_top_dir_name}
        echo "file2 top       : ${file2_top_dir}"

        # Finally copy is performed
        copy_flag=1
        install -d ${_GFX_UM_INST_DIR}
        install -m 0644 ${file1_top_dir}/${user_pkg_name}/Software/${user_copyfile_name} ${_GFX_UM_INST_DIR}
        install -d ${_GFX_KM_INST_DIR}
        install -m 0644 ${file2_top_dir}/${kern_pkg_name}/Software/${kern_copyfile_name} ${_GFX_KM_INST_DIR}
        echo "Installed GFX package"
        echo "                : ${user_pkg_name}"
        echo "                : ${kern_pkg_name}"
        echo ""
    done

    if [ ${copy_flag} -eq 0 ]; then
        return
    fi

    echo ""
    echo "Packages for GFX module were found and copied."
    echo /=======================================================/
}

##### For Multi Media

# For Audio library copy
# $1: audio_common_top_dir
#
# Global
# _audio_list: audio library data
func_audio_lib()
{
    _audio_common_install=0

    for i in ${_audio_list}
    do
        sw_name=`echo $i | cut -d "," -f 1`
        pkg_name=`echo $i | cut -d "," -f 2`
        copyfile_name=`echo $i | cut -d "," -f 3`
        md5_val=`eval echo '$_MD5_'$pkg_name`
        if [ -n "${_debug}" ]; then
            echo ""
            echo "sw_name         = $sw_name"
            echo "pkg_name        = $pkg_name"
            echo "copyfile_name   = $copyfile_name"
            echo "md5_val         = $md5_val"
        fi

        func_search_and_md5check "${pkg_name}" "${copyfile_name}" "${md5_val}" "${_src_full}"
        if [ -z "${_find_filename}" ]; then
            echo "${sw_name} not found!"
        else
            # install common lib (if not installed)
            if [ ${_audio_common_install} -eq 0 ]; then
                _audio_common_install=1
                echo "Installed Audio Common Library"
                install -d ${_OMX_UM_INST_DIR}
                install -m 644 $1/RTM0AC0000XACMND30SL40C/Software/RTM0AC0000XACMND30SL40C.tar.gz ${_OMX_UM_INST_DIR}
                echo "                : RTM0AC0000XACMND30SL40C"
            fi

            # install omx common lib (if not installed)
            func_install_omx_common

            # install audio lib
            file_top_dir=${_extract_top_dir_name}
            install -m 0644 ${file_top_dir}/${pkg_name}/Software/${copyfile_name} ${_OMX_UM_INST_DIR}
            echo "Installed $sw_name"
            echo "                : ${pkg_name}"
        fi
    done
}

# For Audio common library
func_audio()
{
    echo ""
    echo "Copying for Audio Common Packages"

    # OMX Media Component Audio Common Library for Linux
    func_search_and_md5check "RTM0AC0000XACMND30SL40C" \
        "RTM0AC0000XACMND30SL40C.tar.gz" "${_MD5_RTM0AC0000XACMND30SL40C}" "${_src_full}"

    if [ -z "${_find_filename}" ]; then
        echo "Audio Common Library not found!"
        echo ""
        return
    fi

    audio_common_top_dir=${_extract_top_dir_name}
#    echo "audio common top: ${audio_common_top_dir}"
    _audio_common_install=0

    # Audio requires OMX common library
    if [ ${_omx_common_install} -eq 0 ]; then
        func_search_omx_common
        if [ -z "${_find_filename}" ]; then
            echo "OMX Common Library not found!"
            echo ""
            return
        fi
    else
        echo "OMX Common Library already installed"
    fi

    # Audio Library
    func_audio_lib ${audio_common_top_dir}

    echo ""
    echo "Packages for Audio module were found and copied."
    echo /=======================================================/
}

# For Audio M/W
func_audio_mw()
{
    echo ""
    echo "Copying for Audio M/W Packages"

    copy_flag=0

    for i in ${_audio_mw_list}
    do
        sw_name=`echo $i | cut -d "," -f 1`
        pkg_name=`echo $i | cut -d "," -f 2`
        copyfile_name=`echo $i | cut -d "," -f 3`
        md5_val=`eval echo '$_MD5_'$pkg_name`
        if [ -n "${_debug}" ]; then
            echo ""
            echo "sw_name         = $sw_name"
            echo "pkg_name        = $pkg_name"
            echo "copyfile_name   = $copyfile_name"
            echo "md5_val         = $md5_val"
        fi

        func_search_and_md5check "${pkg_name}" "${copyfile_name}" "${md5_val}" "${_src_full}"
        if [ -z "${_find_filename}" ]; then
            echo "${sw_name} not found!"
        else
            # install audio m/w lib
            copy_flag=1
            file_top_dir=${_extract_top_dir_name}
            install -d ${_OMX_UM_INST_DIR}
            install -m 0644 ${file_top_dir}/${pkg_name}/Software/${copyfile_name} ${_OMX_UM_INST_DIR}
            echo "Installed $sw_name"
            echo "                : ${pkg_name}"
        fi
    done

    if [ ${copy_flag} -eq 0 ]; then
        return
    fi

    echo ""
    echo "Packages for Audio M/W module were found and copied."
    echo /=======================================================/
}

# search & MD5 check for OMX common library (Audio & Video)
# return
# _omx_common_top_dir: omx common lib top dir
func_search_omx_common()
{
    echo ""
    echo "Search & MD5 check for OMX Common Packages"

    func_search_and_md5check "RTM0AC0000XCMCTL30SL40C" \
        "RTM0AC0000XCMCTL30SL40C.tar.bz2" "${_MD5_RTM0AC0000XCMCTL30SL40C}" "${_src_full}"

    if [ -z "${_find_filename}" ]; then
        return
    fi

    _omx_common_top_dir=${_extract_top_dir_name}
}

# install OMX common library
func_install_omx_common()
{
    if [ ${_omx_common_install} -eq 0 ]; then
        echo ""
        echo "Install for OMX Common Packages"
        install -d ${_OMX_UM_INST_DIR}
        install -m 644 ${_omx_common_top_dir}/RTM0AC0000XCMCTL30SL40C/Software/RTM0AC0000XCMCTL30SL40C.tar.bz2 ${_OMX_UM_INST_DIR}
        install -d ${_UVCS_INST_DIR}
        echo "                : RTM0AC0000XCMCTL30SL40C"
        _omx_common_install=1
    fi
}

# search & MD5 check for UVCS driver
# return
# _video_uvcs_top_dir: uvcs top dir
func_search_uvcs()
{
    echo ""
    echo "Search & MD5 check for UVCS Packages"

    # UVCS driver
    func_search_and_md5check "RCG3VUDRL4001ZDO" \
        "RCG3VUDRL4001ZDO.tar.bz2" "${_MD5_RCG3VUDRL4001ZDO}" "${_src_full}"

    _video_uvcs_top_dir=${_extract_top_dir_name}
}

# install uvcs driver
func_install_uvcs()
{
    if [ ${_uvcs_install} -eq 0 ]; then
        echo ""
        echo "Installed UVCS driver"
        install -d ${_UVCS_INST_DIR}
        install -m 644 ${_video_uvcs_top_dir}/RCG3VUDRL4001ZDO/Software/RCG3VUDRL4001ZDO.tar.bz2 ${_UVCS_INST_DIR}
        echo "                : RCG3VUDRL4001ZDO"
        _uvcs_install=1
    fi
}

# search & MD5 check for OMX Video Decoder library
# Global
# _video_dec_list: video decoder list
func_video_decoder_lib()
{
    echo ""
    echo "Copying for Video Decoder Library Packages"

    # Decoder Common Library
    func_search_and_md5check "RTM0AC0000XVCMND30SL40C" \
        "RTM0AC0000XVCMND30SL40C.tar.bz2" "${_MD5_RTM0AC0000XVCMND30SL40C}" "${_src_full}"

    if [ -z "${_find_filename}" ]; then
        echo "OMX Video Decoder Common Library not found!"
        return
    fi

    video_decoder_common_top_dir=${_extract_top_dir_name}
    _video_decoder_common_install=0

    # Video Decoder Library
    for i in ${_video_dec_list}
    do
        sw_name=`echo $i | cut -d "," -f 1`
        pkg_name=`echo $i | cut -d "," -f 2`
        copyfile_name=`echo $i | cut -d "," -f 3`
        md5_val=`eval echo '$_MD5_'$pkg_name`
        if [ -n "${_debug}" ]; then
            echo ""
            echo "sw_name         = $sw_name"
            echo "pkg_name        = $pkg_name"
            echo "copyfile_name   = $copyfile_name"
            echo "md5_val         = $md5_val"
        fi

        func_search_and_md5check "${pkg_name}" "${copyfile_name}" "${md5_val}" "${_src_full}"
        if [ -z "${_find_filename}" ]; then
            echo "${sw_name} not found!"
        else
            # install OMX common lib (if not installed)
            func_install_omx_common

            # install UVCS driver (if not installed)
            func_install_uvcs

            # install video decoder common lib (if not installed)
            if [ ${_video_decoder_common_install} -eq 0 ]; then
                _video_decoder_common_install=1
                echo "Installed Video Decoder Common Library"
                install -m 644 ${video_decoder_common_top_dir}/RTM0AC0000XVCMND30SL40C/Software/RTM0AC0000XVCMND30SL40C.tar.bz2 ${_OMX_UM_INST_DIR}
                echo "                : RTM0AC0000XVCMND30SL40C"
            fi

            # install searched library
            file_top_dir=${_extract_top_dir_name}
            install -m 644 ${file_top_dir}/${pkg_name}/Software/${copyfile_name} ${_OMX_UM_INST_DIR}
            echo "Installed ${sw_name}"
            echo "                : ${pkg_name}"
        fi
    done
}

# search & MD5 check for OMX Video Encoder library
# Global
# _video_enc_list: video encoder list
func_video_encoder_lib()
{
    echo ""
    echo "Copying for Video Encoder Library Packages"
    # Encoder Common Library
    func_search_and_md5check "RTM0AC0000XVCMNE30SL40C" \
        "RTM0AC0000XVCMNE30SL40C.tar.bz2" "${_MD5_RTM0AC0000XVCMNE30SL40C}" "${_src_full}"

    if [ -z "${_find_filename}" ]; then
        echo "OMX Video Encoder Common Library not found!"
        return
    fi

    video_encoder_common_top_dir=${_extract_top_dir_name}
    _video_encoder_common_install=0

    # Video Encoder Library
    for i in ${_video_enc_list}
    do
        sw_name=`echo $i | cut -d "," -f 1`
        pkg_name=`echo $i | cut -d "," -f 2`
        copyfile_name=`echo $i | cut -d "," -f 3`
        md5_val=`eval echo '$_MD5_'$pkg_name`
        if [ -n "${_debug}" ]; then
            echo ""
            echo "sw_name         = $sw_name"
            echo "pkg_name        = $pkg_name"
            echo "copyfile_name   = $copyfile_name"
            echo "md5_val         = $md5_val"
        fi

        func_search_and_md5check "${pkg_name}" "${copyfile_name}" "${md5_val}" "${_src_full}"
        if [ -z "${_find_filename}" ]; then
            echo "${sw_name} not found!"
        else
            # install common lib (if not installed)
            func_install_omx_common

            # install UVCS driver (if not installed)
            func_install_uvcs

            # install video encoder common lib (if not installed)
            if [ ${_video_encoder_common_install} -eq 0 ]; then
                _video_encoder_common_install=1
                echo "Installed Video Encoder Common Library"
                install -m 644 ${video_encoder_common_top_dir}/RTM0AC0000XVCMNE30SL40C/Software/RTM0AC0000XVCMNE30SL40C.tar.bz2 ${_OMX_UM_INST_DIR}
                echo "                : RTM0AC0000XVCMNE30SL40C"
            fi

            # install searched library
            file_top_dir=${_extract_top_dir_name}
            install -m 644 ${file_top_dir}/${pkg_name}/Software/${copyfile_name} ${_OMX_UM_INST_DIR}
            echo "Installed ${sw_name}"
            echo "                : ${pkg_name}"
        fi
    done
}

# For Video decoder
func_video_decoder()
{
    echo ""
    echo "Copying for Video Decoder Packages"

    # OMX Common library
    if [ ${_omx_common_install} -eq 0 ]; then
        func_search_omx_common
        if [ -z "${_find_filename}" ]; then
            echo "OMX Common Library not found!"
            echo ""
            return
        fi
    else
        echo "OMX Common Library already installed"
    fi

    # UVCS driver
    if [ ${_uvcs_install} -eq 0 ]; then
        func_search_uvcs
        if [ -z "${_find_filename}" ]; then
            echo "UVCS driver not found!"
            echo ""
            return
        fi
    else
        echo "UVCS driver already installed"
    fi

    # OMX Decoder
    # Decoder common Lib
    func_video_decoder_lib

    echo ""
    echo "Packages for video decoder module were found and copied."
    echo /=======================================================/
}

# For Video encoder
func_video_encoder()
{
    echo ""
    echo "Copying for Video Encoder Packages"

    # OMX Common library
    if [ ${_omx_common_install} -eq 0 ]; then
        func_search_omx_common
        if [ -z "${_find_filename}" ]; then
            echo "OMX Common Library not found!"
            echo ""
            return
        fi
    else
        echo "OMX Common Library already installed"
    fi

    # UVCS driver
    if [ ${_uvcs_install} -eq 0 ]; then
        func_search_uvcs
        if [ -z "${_find_filename}" ]; then
            echo "UVCS driver not found!"
            echo ""
            return
        fi
    else
        echo "UVCS driver already installed"
    fi

    # OMX Encoder
    # Encoder common Lib
    func_video_encoder_lib

    echo ""
    echo "Packages for video encoder module were found and copied."
    echo /=======================================================/
}

# For DTV kernel module
func_dtv_kern()
{
    echo ""
    echo "Copying for DTV kernel module"

    _dtv_kern_install=0

    # file check
    func_search_and_md5check "RCG3T001L4001ZDO" "Software.tar.gz" "${_MD5_RCG3T001L4001ZDO}" "${_src_full}"
    if [ -z "${_find_filename}" ]; then
        echo "RCG3T001L4001ZDO:Software.tar.gz not found!"
        return
    fi

    file_top_dir=${_extract_top_dir_name}

    # extract --> archive --> copied
    echo "Installed DTV kernel modules"
    dtv_kern_list="ssp_drv scu_src_drv tsif_drv tddmac_drv"
    tar xf ${file_top_dir}/RCG3T001L4001ZDO/Software/Software.tar.gz
    for i in ${dtv_kern_list}
    do
        tar cfz $i.tar.gz $i
        install -d ${_DTV_KM_INST_DIR}
        install -m 644 $i.tar.gz ${_DTV_KM_INST_DIR}
        echo "                : $i.tar.gz"
    done

    _dtv_kern_install=1

    echo ""
    echo "DTV kernel module were found and copied."
    echo /=======================================================/
}

# For DTV lib
func_dtv_lib()
{
    echo ""
    echo "Copying for DTV Library Packages"

    # DTV kernel modules
    if [ ${_dtv_kern_install} -eq 0 ]; then
        echo "DTV kernel module not found!"
        echo "Skip DTV Package"
        echo ""
        return
    fi

    # file check
    func_search_and_md5check "RTM0RC0000TE020000SL40C" "Software.tar.gz" "${_MD5_RTM0RC0000TE020000SL40C1}" "${_src_full}"
    if [ -z "${_find_filename}" ]; then
        echo "RTM0RC0000TE020000SL40C:Software.tar.gz not found!"
        return
    fi

    func_search_and_md5check "RTM0RC0000TE020000SL40C" "../Reference/Reference.tar.gz" "${_MD5_RTM0RC0000TE020000SL40C2}" "${_src_full}"
    if [ -z "${_find_filename}" ]; then
        echo "RTM0RC0000TE020000SL40C:Reference.tar.gz not found!"
        return
    fi

    # install searched library
    file_top_dir=${_extract_top_dir_name}
    install -d ${_DTV_UM_INST_DIR}
    echo "Installed DTV Library"
    install -m 644 ${file_top_dir}/RTM0RC0000TE020000SL40C/Software/Software.tar.gz ${_DTV_UM_INST_DIR}
    echo "                : RTM0RC0000TE020000SL40C:Software.tar.gz"
    install -m 644 ${file_top_dir}/RTM0RC0000TE020000SL40C/Reference/Reference.tar.gz ${_DTV_UM_INST_DIR}
    echo "                : RTM0RC0000TE020000SL40C:Reference.tar.gz"

    echo ""
    echo "DTV Package were found and copied."
    echo /=======================================================/
}

# For DVD lib
func_dvd_lib()
{
    echo ""
    echo "Copying for DVD Library Packages"

    # file check
    func_search_and_md5check "RTM0RC0000XDVDC301SL40C" "Software.tar.gz" "${_MD5_RTM0RC0000XDVDC301SL40C}" "${_src_full}"
    if [ -z "${_find_filename}" ]; then
        echo "RTM0RC0000XDVDC301SL40C:Software.tar.gz not found!"
        return
    fi

    # install searched library
    file_top_dir=${_extract_top_dir_name}
    install -d ${_DVD_UM_INST_DIR}
    install -m 644 ${file_top_dir}/RTM0RC0000XDVDC301SL40C/Software/Software.tar.gz ${_DVD_UM_INST_DIR}
    echo "Installed DVD Library"
    echo "                : RTM0RC0000XDVDC301SL40C:Software.tar.gz"

    echo ""
    echo "DVD Package were found and copied."
    echo /=======================================================/
}

# For DTV/DVD main routine
func_dtv_dvd()
{
    echo ""
    echo "Copying for DTV/DVD Packages"

    # DTV/DVD requires OMX Video Decoder
    if [ ${_video_decoder_common_install} -eq 0 ]; then
        echo "Video Decoder Library not found!"
        echo "Skip DTV/DVD Package"
        echo ""
        return
    fi

    # DTV requires Audio Decoder
    if [ ${_audio_common_install} -eq 0 ]; then
        echo "Audio Common Library not found!"
        echo "Skip DTV Package"
        echo ""
    else
        # DTV package
        func_dtv_kern
        func_dtv_lib
    fi

    # DVD package
    func_dvd_lib

    echo ""
    echo "Packages for DTV/DVD were found and copied."
    echo /=======================================================/
}

# For CMS main routine
func_cms()
{
    echo ""
    echo "Copying for CMS Packages"

    copy_flag=0

    for i in ${_cms_list}
    do
        sw_name=`echo $i | cut -d "," -f 1`
        pkg_name=`echo $i | cut -d "," -f 2`
        copyfile_name=`echo $i | cut -d "," -f 3`
        md5_val=`eval echo '$_MD5_'$pkg_name`
        if [ -n "${_debug}" ]; then
            echo ""
            echo "sw_name         = $sw_name"
            echo "pkg_name        = $pkg_name"
            echo "copyfile_name   = $copyfile_name"
            echo "md5_val         = $md5_val"
        fi

        func_search_and_md5check "${pkg_name}" "${copyfile_name}" "${md5_val}" "${_src_full}"
        if [ -z "${_find_filename}" ]; then
            echo "${sw_name} not found!"
        else
            # install searched library
            copy_flag=1
            file_top_dir=${_extract_top_dir_name}
            install -d ${_CMS_UM_INST_DIR}
            install -m 644 ${file_top_dir}/${pkg_name}/Software/${copyfile_name} ${_CMS_UM_INST_DIR}
            echo "Installed ${sw_name}"
            echo "                : ${pkg_name}"
        fi
    done

    if [ ${copy_flag} -eq 0 ]; then
        return
    fi

    echo ""
    echo "Packages for CMS were found and copied."
    echo /=======================================================/
}

# For ADSP main routine
func_adsp()
{
    echo ""
    echo "Copying for ADSP Packages"

    # file check only
    for i in ${_adsp_list}
    do
        sw_name=`echo $i | cut -d "," -f 1`
        pkg_name=`echo $i | cut -d "," -f 2`
        copyfile_name=`echo $i | cut -d "," -f 3`
        md5_val=`eval echo '$_MD5_'$pkg_name`
        if [ -n "${_debug}" ]; then
            echo ""
            echo "sw_name         = $sw_name"
            echo "pkg_name        = $pkg_name"
            echo "copyfile_name   = $copyfile_name"
            echo "md5_val         = $md5_val"
        fi

        func_search_and_md5check "${pkg_name}" "${copyfile_name}" "${md5_val}" "${_src_full}"
        if [ -z "${_find_filename}" ]; then
            echo "${sw_name} not found!"
            echo "Skip ADSP Package"
            echo ""
            return
        fi
    done

    # file check and install
    for i in ${_adsp_list}
    do
        sw_name=`echo $i | cut -d "," -f 1`
        pkg_name=`echo $i | cut -d "," -f 2`
        copyfile_name=`echo $i | cut -d "," -f 3`
        md5_val=`eval echo '$_MD5_'$pkg_name`
        if [ -n "${_debug}" ]; then
            echo ""
            echo "sw_name         = $sw_name"
            echo "pkg_name        = $pkg_name"
            echo "copyfile_name   = $copyfile_name"
            echo "md5_val         = $md5_val"
        fi

        func_search_and_md5check "${pkg_name}" "${copyfile_name}" "${md5_val}" "${_src_full}"
        if [ -z "${_find_filename}" ]; then
            echo "${sw_name} not found!"
        else
            # install searched library
            file_top_dir=${_extract_top_dir_name}
            install -d ${_ADSP_KM_INST_DIR}
            install -d ${_ADSP_UM_INST_DIR}
            if [ "${sw_name}" = "adsp_driver" ]; then
                install -m 644 ${file_top_dir}/${pkg_name}/Software/${copyfile_name} ${_ADSP_KM_INST_DIR}
            else
                install -m 644 ${file_top_dir}/${pkg_name}/Software/${copyfile_name} ${_ADSP_UM_INST_DIR}
            fi
            echo "Installed ${sw_name}"
            echo "                : ${pkg_name}"
        fi
    done

    echo ""
    echo "Packages for ADSP were found and copied."
    echo /=======================================================/
}

# For ICCOM main routine
func_iccom()
{
    echo ""
    echo "Copying for ICCOM Packages"

    # file check only
    for i in ${_iccom_list}
    do
        sw_name=`echo $i | cut -d "," -f 1`
        pkg_name=`echo $i | cut -d "," -f 2`
        copyfile_name=`echo $i | cut -d "," -f 3`
        md5_name=`echo $i | cut -d "," -f 4`
        md5_val=`eval echo '$_MD5_'${md5_name}`
        if [ -n "${_debug}" ]; then
            echo ""
            echo "sw_name         = $sw_name"
            echo "pkg_name        = $pkg_name"
            echo "copyfile_name   = $copyfile_name"
            echo "md5_val         = $md5_val"
        fi

        func_search_and_md5check "${pkg_name}" "${copyfile_name}" "${md5_val}" "${_src_full}"
        if [ -z "${_find_filename}" ]; then
            echo "${sw_name} not found!"
            echo "Skip ICCOM Package"
            echo ""
            return
        fi
    done

    # file check and install
    for i in ${_iccom_list}
    do
        sw_name=`echo $i | cut -d "," -f 1`
        pkg_name=`echo $i | cut -d "," -f 2`
        copyfile_name=`echo $i | cut -d "," -f 3`
        md5_name=`echo $i | cut -d "," -f 4`
        md5_val=`eval echo '$_MD5_'${md5_name}`
        if [ -n "${_debug}" ]; then
            echo ""
            echo "sw_name         = $sw_name"
            echo "pkg_name        = $pkg_name"
            echo "copyfile_name   = $copyfile_name"
            echo "md5_val         = $md5_val"
        fi

        func_search_and_md5check "${pkg_name}" "${copyfile_name}" "${md5_val}" "${_src_full}"
        if [ -z "${_find_filename}" ]; then
            echo "${sw_name} not found!"
        else
            # install searched library
            file_top_dir=${_extract_top_dir_name}
            install -d ${_ICCOM_KM_INST_DIR}
            install -d ${_ICCOM_UM_INST_DIR}
            if [ "${sw_name}" = "iccom_lib" ]; then
                install -m 644 ${file_top_dir}/${pkg_name}/Software/${copyfile_name} ${_ICCOM_UM_INST_DIR}
            else
                install -m 644 ${file_top_dir}/${pkg_name}/Software/${copyfile_name} ${_ICCOM_KM_INST_DIR}
            fi
            echo "Installed ${sw_name}"
            echo "                : ${pkg_name}"
        fi
    done

    echo ""
    echo "Packages for ICCOM were found and copied."
    echo /=======================================================/
}

################################
# Copy Script Main routine
################################
echo "Copyscript for R-Car Gen3"
echo
#### 1) Checking current directory
if [ ! -d meta-rcar-gen3 ]; then
    echo "ERROR: Please extract meta-renesas and cd to it, before execute $0"
    exit 1
fi

#### 2) Checking Arguments
if [ "X$1" = "X" ]; then
    usage
    exit 1
fi

while [ $# -gt 0 ] ; do
    case "$1" in
        -f|--force)
            _no_md5check=1
            ;;
        -d|--debug)
            _debug=1
            ;;
        *)
            _src_dirname=$(basename $1)
            _src_path=$(cd $(dirname $1) && pwd)
            _src_full=${_src_path}/${_src_dirname}
            ;;
    esac
    shift
done

# source directory check
if [ ! -d ${_src_path}/${_src_dirname} ]; then
    echo "${_src_path}/${_src_dirname} not found."
    usage
    exit 1
fi

if [ -n "${_debug}" ]; then
    echo "src             = ${_src_dirname}"
    echo "src_path        = ${_src_path}"
    echo "src_full        = ${_src_full}"
    echo "no_md5check     = ${_no_md5check}"
    echo ""
fi

##### 3) create temp directory
TMPWORK=${PWD}/CP_SCRIPT_TEMP
if [ -d ${TMPWORK} ]; then
    echo "ERROR: Work directory already exist."
    exit 1
fi
install -d -m 700 ${TMPWORK}
cd ${TMPWORK}

##### 4) copy
# initialize flag
_omx_common_install=0
_uvcs_install=0
_audio_common_install=0
_video_decoder_common_install=0
_video_encoder_common_install=0
_dtv_kern_install=0

func_gfx
func_audio
func_audio_mw
func_video_decoder
func_video_encoder
func_dtv_dvd
func_cms
func_adsp
func_iccom

##### 5) cleanup temp directory
func_clean_tempdir

##### End
echo "Complete copying !"
