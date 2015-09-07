#!/bin/sh

# Copyright (C) Renesas Electronics Corporation 2015 All rights reserved.
#
echo "Copyscript for RCar-Gen3"
#### 1) Checking Arguments
#
if [ "X$1" = "X-v" ]; then
    Vopt=-1;
    shift;
    echo "${version}"
fi
if [ "X$1" = "X" -o ! -d $1 ]; then
    echo "Usage : $0 <Source Directory Storing CD-Images>"
    exit
fi

if [ ! -d meta-rcar-gen3 ]; then
   echo "ERROR : Please extract meta-renesas and cd to it, before execute $0"
   exit
fi


#### 2) Configures ("1": enable copying, "0": disable copying)
# Configure to enable/disable copying for GSX
GLES_ENABLE="1"

# Configure for output log
export DBG_LOG_DEFAULT=1
### End CONFIGURE PART.........................................

#### 3) Make source/destination directory and extract files
# Make working dir.
#
TMPWRK=${PWD}/RCG3_RCP_$$
mkdir -p ${TMPWRK}
mkdir -p ${TMPWRK}/ZIPs

# Set Source / Destination Directory
#
export SRCWRK=${PWD}/RCG3_SRC_$$  # make to keep original source clean after extracting
mkdir -p ${SRCWRK}
cp -rfP $1 ${SRCWRK}
chmod -R 777  ${SRCWRK}
export DSTWRK=`pwd`

#
# Extract Zip files on the deliverables
#
cd ${TMPWRK}/ZIPs
find ${SRCWRK} -name '*.zip' -print |\
sed 's:\(.*/\)\([^/]*\)\(.zip\):unzip -qq -d \2 \1\2\3:' |\
sh

find ${TMPWRK}/ZIPs -name '*.zip' -print > ${TMPWRK}/ZIPs-list
touch ${TMPWRK}/ZIPs-list0
while ! cmp -s ${TMPWRK}/ZIPs-list ${TMPWRK}/ZIPs-list0; do
    find ${TMPWRK}/ZIPs -name '*.zip' -print |\
    sed 's:\(.*/\)\([^/]*\)\(.zip\):unzip -qq -d \2 \1\2\3:' |\
    sh
    cp -rP ${TMPWRK}/ZIPs-list ${TMPWRK}/ZIPs-list0
    find ${TMPWRK}/ZIPs -name '*.zip' -print > ${TMPWRK}/ZIPs-list
done

# Extract .tar.(bz2/gz/xz) files on the deliverables
#
find ${TMPWRK}/ZIPs ${SRCWRK} -name '*.tar.*' -print |\
sed 's:\(.*/\)\(.*\)\(.tar\)\(.*\): tar xf \1\2\3\4 -C \1:' |\
sh

cd ${DSTWRK}
#
# List up contents
#
find ${TMPWRK}/ZIPs -print > ${TMPWRK}/pkgdirname.txt
find ${SRCWRK} -print >> ${TMPWRK}/pkgdirname.txt
##-------------------------------------
# Function prints debug log
# $0: source of package (after 'sed')
# $1: name of the package
DBG_LOG() {
    if [ X1 = X$DBG_LOG_DEFAULT -o X$Vopt != X ]; then
        test X$1 = X && echo $2 NOT found || echo $2 found and copying ...
    fi
}

##--------------------------------------------
echo "Source is copying. Please wait..."
#### 4) Start copy source ...................................
## Start copying source for Graphic (Gfx) packages ##
if [ "X$GLES_ENABLE" = "X1" ]; then
    ### GLES kernel module
    if [ -d ./meta-rcar-gen3/recipes-kernel/kernel-module-gles/kernel-module-gles/ ]; then
        # Copy for Salvator-X
        Rogue_KM_H3=`egrep 'RCH3G001L4001ZDI/GSX_KM_H3\.tar\.bz2$' ${TMPWRK}/pkgdirname.txt | tail -1`
        if [ "X${Rogue_KM_H3}" != "X" ]; then
            test X$Vopt = X || echo Rogue_KM_H3 Found
            cp -prP ${Rogue_KM_H3} ${DSTWRK}/./meta-rcar-gen3/recipes-kernel/kernel-module-gles/kernel-module-gles/
        fi
        DBG_LOG "${Rogue_KM_H3}" "Rogue_KM_H3"
    fi
    ##
    ## GLES user module
    if [ -d ./meta-rcar-gen3/recipes-graphics/gles-module/gles-user-module/ ]; then
        # Copy for Salvator-X
        Rogue_Libs_gles3_h3=`egrep 'RTM0RC7795GLTG0001SL40C/release/r8a7795_linux_gsx_binaries_gles3\.tar\.bz2$' ${TMPWRK}/pkgdirname.txt | tail -1`
        if [ "X${Rogue_Libs_gles3_h3}" != "X" ]; then
          test X$Vopt = X || echo Rogue_Libs_gles3_es2 Found
          cp -prP ${Rogue_Libs_gles3_h3} ${DSTWRK}/./meta-rcar-gen3/recipes-graphics/gles-module/gles-user-module/
        fi
        DBG_LOG "${Rogue_Libs_gles3_h3}" "Rogue_Libs_gles3_h3"
    fi
fi
## End copying source for Graphic (Gfx) packages ##

#### 5) Remove "-x" flag of regular files
for i in `find ./meta-rcar-gen3/ -type f` ; do
    if [ ${i} != "./meta-rcar-gen3/docs/sample/copyscript/copy_proprietary_softwares.sh" ] ; then
        chmod a-x $i
    fi
done

#Convert all 'End of Line/New Line' from Window type to Linux type
if [ -f /usr/bin/dos2unix ]; then
    find ./meta-rcar-gen3/ \( -name \*.patch* -prune -o -name \*.bb* -o -name \*.conf* -o -name \*.inc* \) \
    -exec /usr/bin/dos2unix -q "{}"  2> /dev/null \;
fi

#### 6) Clean up temp dir
if [ -f ${TMPWRK}/pkgdirname.txt ]; then
    /bin/rm -rf ${TMPWRK}
    echo ""
fi

if [ -d ${SRCWRK} ]; then
    /bin/rm -rf ${SRCWRK}
    echo ""
fi

echo "Complete copying !"
