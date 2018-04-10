#!/bin/sh

# Copyright (C) Renesas Electronics Corporation 2016-2018 All rights reserved.

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
_audio_list="AAC-LC_decoder_lib,EVARTM0AC0000XAAACD30SL41C,RTM0AC0000XAAACD30SL41C.tar.gz \
             AAC-LC_encoder_lib,EVARTM0AC0000XAAACE30SL41C,RTM0AC0000XAAACE30SL41C.tar.gz"

# Audio M/W Library
# Please add omx audio library to "_audio_mw_list"
# Don't use space in xxx_name.
# audio_mw_list="<software_name>,<package_name>,<copy_file_name> \
#                <software_name>,<package_name>,<copy_file_name> \
#                <software_name>,<package_name>,<copy_file_name>"
_audio_mw_list="AAC-LC_decoder_M/W,EVARTM0AC0000ADAACMZ1SL41C,RTM0AC0000ADAACMZ1SL41C.tar.gz \
                AAC-LC_encoder_M/W,EVARTM0AC0000AEAACMZ1SL41C,RTM0AC0000AEAACMZ1SL41C.tar.gz"

# Video Decoder Library
# Please add omx video decoder library to "_video_dec_list"
# Don't use space in xxx_name.
# video_dec_list="<software_name>,<package_name>,<copy_file_name> \
#                 <software_name>,<package_name>,<copy_file_name> \
#                 <software_name>,<package_name>,<copy_file_name>"
_video_dec_list="H264_decoder,EVARTM0AC0000XV264D30SL41C,EVARTM0AC0000XV264D30SL41C.tar.bz2"

# Video Encoder Library
# Please add omx video encoder library to "_video_enc_list"
# Don't use space in xxx_name.
# video_enc_list="<software_name>,<package_name>,<copy_file_name> \
#                 <software_name>,<package_name>,<copy_file_name> \
#                 <software_name>,<package_name>,<copy_file_name>"
_video_enc_list="H264_encoder,EVARTM0AC0000XV264E30SL41C,EVARTM0AC0000XV264E30SL41C.tar.bz2"

# Common library packages
# Don't use space in xxx_name.
# XXX_list="<software_name>,<package_name>,<copy_file_name> \
#           <software_name>,<package_name>,<copy_file_name> \
#           <software_name>,<package_name>,<copy_file_name>"
_omx_common_list="omx_common_lib,EVARTM0AC0000XCMCTL30SL41C,EVARTM0AC0000XCMCTL30SL41C.tar.bz2"
_uvcs_list="uvcs_lib,RCG3VUDRL4101ZDO,RCG3VUDRL4101ZDO.tar.bz2"
_video_enc_common_list="video_enc_common,EVARTM0AC0000XVCMNE30SL41C,EVARTM0AC0000XVCMNE30SL41C.tar.bz2"
_video_dec_common_list="video_dec_common,EVARTM0AC0000XVCMND30SL41C,EVARTM0AC0000XVCMND30SL41C.tar.bz2"
_audio_common_list="audio_common,EVARTM0AC0000XACMND30SL41C,RTM0AC0000XACMND30SL41C.tar.gz"

# GFX
# Please add GFX to "_gfx_list"
# Don't use space in xxx_name.
# gfx_list="<package_name(user)>,<copy_file_name(user)>,<package_name(kernel)>,<copy_file_name(kernel)> \
#           <package_name(user)>,<copy_file_name(user)>,<package_name(kernel)>,<copy_file_name(kernel)> \
#           <package_name(user)>,<copy_file_name(user)>,<package_name(kernel)>,<copy_file_name(kernel)>"
_gfx_list="EVARTM0RC7795GLPG0001SL41C,EVA_r8a77951_linux_gsx_binaries_gles.tar.bz2,RCH3G001L4101ZDO,GSX_KM_H3.tar.bz2 \
           EVARTM0RC7796GLPG0001SL41C,EVA_r8a77960_linux_gsx_binaries_gles.tar.bz2,RCM3G001L4101ZDO,GSX_KM_M3.tar.bz2"

##### static value
_MODE_ZIP=1
_MODE_TAR=2
_GFX_KM_INST_DIR="../meta-rcar-gen3/recipes-kernel/kernel-module-gles/kernel-module-gles"
_GFX_UM_INST_DIR="../meta-rcar-gen3/recipes-graphics/gles-module/gles-user-module"
_UVCS_INST_DIR="../meta-rcar-gen3/recipes-kernel/kernel-module-uvcs/kernel-module-uvcs-drv"
_OMX_UM_INST_DIR="../meta-rcar-gen3/recipes-multimedia/omx-module/omx-user-module"

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

    # set result
    if [ 1 = $zip_count ]; then
        _find_filename=$(ls ${_search_dir}/$1*.zip)
        _extract_mode=${_MODE_ZIP}
    elif [ 1 = $tar_count ]; then
        _find_filename=$(ls ${_search_dir}/$1*.tar.*)
        _extract_mode=${_MODE_TAR}
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
# $2: search target filename
# $3: search directory (full path)
# return
# _find_filename : the found file (full path)
# _extract_top_dir_name
func_search_file_in_package()
{
    # search package file
    func_cmn_find_file $1 $3
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

    # Get directory name
    # {PATH}/Package_Version.tar.gz or XXXX.zip --> Package_Version
    top_dir=$(basename ${_find_filename})
    top_dir=${top_dir%.*}

    # search file
    num=`find ${top_dir} -name $2 | wc -l`
    if [ ${num} -eq 1 ]; then
        _find_filename=`find ${top_dir} -name $2`
    else
        # same filename exists.
        _find_filename=`find ${top_dir} -name $2 | grep Software`
    fi

    # set mode
    if [ `echo ${_find_filename} | grep '\.'zip` ]; then
        _extract_mode=${_MODE_ZIP}
    else
        _extract_mode=${_MODE_TAR}
    fi

    # set return value
    _extract_top_dir_name=${top_dir}
}

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
#    func_cmn_md5_check "${top_dir}/$1/Software/$2" "$3"

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

##### File search and MD5check for Package list
# $1: package list
# $2: rigid flag (1: true, other: false)
#
# return
# 1: [Success] One or more files were found.
# 0: [Fail] File not found
#
# package list format
# list ="<software_name>,<package_name>,<copy_file_name>,<md5_variable_name> \
#        <software_name>,<package_name>,<copy_file_name>,<md5_variable_name> \
#        <software_name>,<package_name>,<copy_file_name>,<md5_variable_name>"
#
# Note) Don't use space in xxx_name.
# Note) md5_variable_name is defined in md5list.txt.
#       Prefix "_MD5_" is added automaticary. ex) FOO --> _MD5_FOO
#       It is omissible. The default is "_MD5_<package_name>".
func_list_search_and_md5check ()
{
    find_flag=0

    for i in $1
    do
        sw_name=`echo $i | cut -d "," -f 1`
        pkg_name=`echo $i | cut -d "," -f 2`
        copyfile_name=`echo $i | cut -d "," -f 3`
        md5_val=`echo $i | cut -d "," -f 4`

        # <MD5_name> is empty. Default MD5 name is "_MD5_<pkg_name>"
        if [ -z "${md5_val}" ]; then
            md5_val=`eval echo '$_MD5_'${pkg_name}`
        else
            md5_val=`eval echo '$_MD5_'${md5_val}`
        fi

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
            # rigid flag = TRUE. Not found = ERROR
            if [ "X$2" = "X1" ]; then
                return 0
            fi
        else
            find_flag=1
        fi
    done

    return ${find_flag}
}

##### File search and install (without MD5check) for Package list
#
# $1: package list
# $2: install directory
#
# return
# 1: [Success] One or more files were installed.
# 0: [Fail] File not found
#
# package list format
# list ="<software_name>,<package_name>,<copy_file_name>,<md5_variable_name> \
#        <software_name>,<package_name>,<copy_file_name>,<md5_variable_name> \
#        <software_name>,<package_name>,<copy_file_name>,<md5_variable_name>"
#
# Note) Don't use space in xxx_name.
# Note) md5_variable_name is defined in md5list.txt.
#       Prefix "_MD5_" is added automaticary. ex) FOO --> _MD5_FOO
#       It is omissible. The default is "_MD5_<package_name>".
func_list_search_and_install_wo_md5check()
{
    find_flag=0

    for i in $1
    do
        sw_name=`echo $i | cut -d "," -f 1`
        pkg_name=`echo $i | cut -d "," -f 2`
        copyfile_name=`echo $i | cut -d "," -f 3`
        md5_val=`echo $i | cut -d "," -f 4`

        # <MD5_name> is empty. Default MD5 name is "_MD5_<pkg_name>"
        if [ -z "${md5_val}" ]; then
            md5_val=`eval echo '$_MD5_'$pkg_name`
        else
            md5_val=`eval echo '$_MD5_'${md5_val}`
        fi

        copyfile_name=$(basename ${copyfile_name})

        if [ -n "${_debug}" ]; then
            echo ""
            echo "sw_name         = $sw_name"
            echo "pkg_name        = $pkg_name"
            echo "copyfile_name   = $copyfile_name"
            echo "md5_val         = $md5_val"
        fi

        # file search
        func_search_file_in_package "${pkg_name}" "${copyfile_name}" "${_src_full}"
        if [ -z "${_find_filename}" ]; then
            echo "${sw_name} not found!"
        else
            find_flag=1

            # install
            install -d $2
            install -m 0644 ${_find_filename} $2
            echo "Installed $sw_name"
            echo "                : ${pkg_name}"
        fi
    done

    return ${find_flag}
}

##### File search and install for Package list
#
# $1: package list
# $2: install directory
#
# return
# 1: [Success] One or more files were installed.
# 0: [Fail] File not found
#
# package list format
# list ="<software_name>,<package_name>,<copy_file_name>,<md5_variable_name> \
#        <software_name>,<package_name>,<copy_file_name>,<md5_variable_name> \
#        <software_name>,<package_name>,<copy_file_name>,<md5_variable_name>"
#
# Note) Don't use space in xxx_name.
# Note) md5_variable_name is defined in md5list.txt.
#       Prefix "_MD5_" is added automaticary. ex) FOO --> _MD5_FOO
#       It is omissible. The default is "_MD5_<package_name>".
func_list_search_and_install()
{
    find_flag=0

    for i in $1
    do
        sw_name=`echo $i | cut -d "," -f 1`
        pkg_name=`echo $i | cut -d "," -f 2`
        copyfile_name=`echo $i | cut -d "," -f 3`
        md5_val=`echo $i | cut -d "," -f 4`

        # <MD5_name> is empty. Default MD5 name is "_MD5_<pkg_name>"
        if [ -z "${md5_val}" ]; then
            md5_val=`eval echo '$_MD5_'$pkg_name`
        else
            md5_val=`eval echo '$_MD5_'${md5_val}`
        fi

        if [ -n "${_debug}" ]; then
            echo ""
            echo "sw_name         = $sw_name"
            echo "pkg_name        = $pkg_name"
            echo "copyfile_name   = $copyfile_name"
            echo "md5_val         = $md5_val"
        fi

        # seach & MD5 check
        func_search_and_md5check "${pkg_name}" "${copyfile_name}" "${md5_val}" "${_src_full}"
        if [ -z "${_find_filename}" ]; then
            echo "${sw_name} not found!"
        else
            find_flag=1

            # Get directory name
            # _find_filename = pkg file (full path). It is not copyfile.
            top_dir=$(basename ${_find_filename})
            top_dir=${top_dir%.*}

            # install
            install -d $2
            install -m 0644 ${top_dir}/${pkg_name}/Software/${copyfile_name} $2
            echo "Installed $sw_name"
            echo "                : ${pkg_name}"
        fi
    done

    return ${find_flag}
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
        install -m 0644 ${file1_top_dir}/${user_pkg_name}/Software/${user_copyfile_name} ${_GFX_UM_INST_DIR}/`echo ${user_copyfile_name} | cut -b 5-200`
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
#
# Global
# _audio_list: audio library list
# Return
# 0: Not found
# 1: Success
func_audio_lib()
{
    # MD5 check: OMX Media Component Audio Common Library for Linux
    func_list_search_and_md5check "${_audio_common_list}" "1"
    if [ $? -eq 0 ]; then
        echo "Audio Common Library not found!"
        echo ""
        return 0
    fi

    # MD5 check: audio library.
    func_list_search_and_md5check "${_audio_list}"
    if [ $? -eq 0 ]; then
        # library not found.
        echo ""
        echo "Audio library not found."
        return 0
    fi

    # Add audio common library to list
    _audio_list="${_audio_common_list} ${_audio_list}"

    # Install omx common lib (if not installed)
    func_install_omx_common

    # Install Audio library packages
    func_list_search_and_install_wo_md5check "${_audio_list}" "${_OMX_UM_INST_DIR}"
    _audio_common_install=1

    return 1
}

# For Audio library
func_audio()
{
    echo ""
    echo "Copying for Audio Common Packages"

    _audio_common_install=0

    # Audio requires OMX common library
    if [ ${_omx_common_install} -eq 0 ]; then
        # MD5 check (rigid flag=TRUE)
        func_list_search_and_md5check "${_omx_common_list}" "1"
        if [ $? -eq 0 ]; then
            echo "OMX Common Library not found!"
            echo ""
            return
        fi
    else
        echo "OMX Common Library already installed"
    fi

    # Audio Library
    func_audio_lib
    if [ $? -eq 0 ]; then
        echo ""
        echo "Skip Audio Packages"
        echo ""
        return
    fi

    echo ""
    echo "Packages for Audio module were found and copied."
    echo /=======================================================/
}

# For Audio M/W
# Global
# _audio_mw_list: audio M/W library list
# Return
# 0: Not found
# 1: Success
func_audio_mw()
{
    echo ""
    echo "Copying for Audio M/W Packages"

    # MD5 check
    func_list_search_and_md5check "${_audio_mw_list}"
    if [ $? -eq 0 ]; then
        # library not found.
        echo ""
        echo "Packages for Audio M/W module were not found."
        return 0
    fi

    # Install Audio M/W library packages
    func_list_search_and_install_wo_md5check "${_audio_mw_list}" "${_OMX_UM_INST_DIR}"

    echo ""
    echo "Packages for Audio M/W module were found and copied."
    echo /=======================================================/

    return 1
}

# install OMX common library
func_install_omx_common()
{
    if [ ${_omx_common_install} -eq 0 ]; then
        echo ""
        echo "Install for OMX Common Packages"
        func_list_search_and_install_wo_md5check "${_omx_common_list}" "${_OMX_UM_INST_DIR}"
        _omx_common_install=1
    fi
}

# install uvcs driver
func_install_uvcs()
{
    if [ ${_uvcs_install} -eq 0 ]; then
        echo ""
        echo "Installed UVCS driver"
        func_list_search_and_install_wo_md5check "${_uvcs_list}" "${_UVCS_INST_DIR}"
        _uvcs_install=1
    fi
}

# search & MD5 check for OMX Video Decoder library
# Global
# _video_dec_list: video decoder list
# Return
# 0: Not found
# 1: Success
func_video_decoder_lib()
{
    echo ""
    echo "Copying for Video Decoder Library Packages"

    # MD5 check: Decoder Common Library (rigid flag=TRUE)
    func_list_search_and_md5check "${_video_dec_common_list}" "1"
    if [ $? -eq 0 ]; then
        echo "OMX Video Decoder Common Library not found!"
        return 0
    fi

    # MD5 check: Video Decoder Library
    func_list_search_and_md5check "${_video_dec_list}"
    if [ $? -eq 0 ]; then
        # library not found.
        return 0
    fi

    # install OMX common lib (if not installed)
    func_install_omx_common

    # install UVCS driver (if not installed)
    func_install_uvcs

    # Add video decoder common lib to list
    _video_dec_list="${_video_dec_common_list} ${_video_dec_list}"

    # install searched library
    func_list_search_and_install_wo_md5check "${_video_dec_list}" "${_OMX_UM_INST_DIR}"
    _video_decoder_common_install=1

    return 1
}

# search & MD5 check for OMX Video Encoder library
# Global
# _video_enc_list: video encoder list
# Return
# 0: Not found
# 1: Success
func_video_encoder_lib()
{
    echo ""
    echo "Copying for Video Encoder Library Packages"


    # MD5 check Encoder Common Library (rigid flag=TRUE)
    func_list_search_and_md5check "${_video_enc_common_list}" "1"
    if [ $? -eq 0 ]; then
        echo "OMX Video Encoder Common Library not found!"
        return 0
    fi
    _video_encoder_common_install=0

    # Video Encoder Library
    # MD5 check
    func_list_search_and_md5check "${_video_enc_list}"
    if [ $? -eq 0 ]; then
        # library not found.
        return 0
    fi

    # install common lib (if not installed)
    func_install_omx_common

    # install UVCS driver (if not installed)
    func_install_uvcs

    # Add Video encoder common library to list
    _video_enc_list="${_video_enc_common_list} ${_video_enc_list}"

    # install searched library
    func_list_search_and_install_wo_md5check "${_video_enc_list}" "${_OMX_UM_INST_DIR}"
    _video_encoder_common_install=1

    return 1
}

# For Video decoder
# Global
# _video_dec_list: video decoder list
# Return
# 0: Not found
# 1: Success
func_video_decoder()
{
    echo ""
    echo "Copying for Video Decoder Packages"

    # OMX Common library
    if [ ${_omx_common_install} -eq 0 ]; then
        # MD5 check (rigid flag=TRUE)
        func_list_search_and_md5check "${_omx_common_list}" "1"
        if [ $? -eq 0 ]; then
            echo "OMX Common Library not found!"
            echo ""
            return
        fi
    else
        echo "OMX Common Library already installed"
    fi

    # UVCS driver
    if [ ${_uvcs_install} -eq 0 ]; then
        # MD5 check (rigid flag=TRUE)
        func_list_search_and_md5check "${_uvcs_list}" "1"
        if [ $? -eq 0 ]; then
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
    if [ $? -eq 0 ]; then
        echo ""
        echo "Skip Video Decoder Packages"
        echo ""
        return
    fi

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
        # MD5 check (rigid flag=TRUE)
        func_list_search_and_md5check "${_omx_common_list}" "1"
        if [ $? -eq 0 ]; then
            echo "OMX Common Library not found!"
            echo ""
            return
        fi
    else
        echo "OMX Common Library already installed"
    fi

    # UVCS driver
    if [ ${_uvcs_install} -eq 0 ]; then
        # MD5 check (rigid flag=TRUE)
        func_list_search_and_md5check "${_uvcs_list}" "1"
        if [ $? -eq 0 ]; then
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
    if [ $? -eq 0 ]; then
        echo ""
        echo "Skip Video Encoder Packages"
        echo ""
        return
    fi

    echo ""
    echo "Packages for video encoder module were found and copied."
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

#### 3) Checking ClickThrough present

COUNTFILE=$(ls ${_src_full}/R-Car_Gen3_Series_Evaluation_Software_Package_for_Linux-*.zip  2>/dev/null | wc --lines)
if [ ${COUNTFILE} -eq 0 ]; then
    echo "R-Car_Gen3_Series_Evaluation_Software_Package_for_Linux-*.zip not found!"
    usage
    exit 1
fi
if [ ${COUNTFILE} -gt 1 ]; then
    echo "More than one (${COUNTFILE}) R-Car_Gen3_Series_Evaluation_Software_Package_for_Linux-*.zip file found!"
    echo "Clean your directory \"${_src_full}\""
    exit 1
fi

COUNTFILE=$(ls ${_src_full}/R-Car_Gen3_Series_Evaluation_Software_Package_of_Linux_Drivers-*.zip  2>/dev/null | wc --lines)
if [ ${COUNTFILE} -eq 0 ]; then
    echo "R-Car_Gen3_Series_Evaluation_Software_Package_of_Linux_Drivers-*.zip not found!"
    exit 1
fi
if [ ${COUNTFILE} -gt 1 ]; then
    echo "More than one (${COUNTFILE}) R-Car_Gen3_Series_Evaluation_Software_Package_of_Linux_Drivers-*.zip file found!"
    echo "Clean your directory \"${_src_full}\""
    usage
    exit 1
fi

unzip -d ${_src_full} -oq ${_src_full}/R-Car_Gen3_Series_Evaluation_Software_Package_for_Linux-*.zip
unzip -d ${_src_full} -oq ${_src_full}/R-Car_Gen3_Series_Evaluation_Software_Package_of_Linux_Drivers-*.zip

tar -C ${_src_full} -zxf ${_src_full}/R-Car_Gen3_Series_Evaluation_Software_Package_for_Linux-*.tar.gz --strip-components 2
tar -C ${_src_full} -zxf ${_src_full}/R-Car_Gen3_Series_Evaluation_Software_Package_of_Linux_Drivers-*.tar.gz --strip-components 2

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

func_gfx
func_audio
func_audio_mw
func_video_decoder
func_video_encoder

##### 5) cleanup temp directory
func_clean_tempdir

##### End
echo "Complete copying !"
