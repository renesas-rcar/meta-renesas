#!/bin/sh

if [ ! -d $1/R-Car_Series_Evaluation_Software_Package_for_Linux ]
then
  tar -C $1 -zxf $1/R-Car_Series_Evaluation_Software_Package_for_Linux-*.tar.gz
fi

if [ ! -d $1/R-Car_Series_Evaluation_Software_Package_of_Linux_Drivers ]
then
  tar -C $1 -zxf $1/R-Car_Series_Evaluation_Software_Package_of_Linux_Drivers-*.tar.gz
fi

find_change_name(){
    cur_path=`pwd`
	fileP=`find . -name $1 | tail -1`
	if [ "X${fileP}" != "X" ]; then
		tmp_path=`dirname $fileP | tail -1`
		cd $tmp_path
		mv -f $1 $2
	fi
	cd $cur_path
}

MMTMP=`mktemp -d`
if [ $? -ne 0 ]; then
	echo "$0: Can't create MMTMP directory, exiting..."
	exit 1
fi

MM_DRVs=`find $1 -name RCH2M2MMPRDL001 | tail -1`
cp -rf $MM_DRVs $MMTMP

MM_LIBs=`find $1 -name RCH2M2MMPRLL001 | tail -1`
cp -rf $MM_LIBs $MMTMP

KERNEL_MODULES="$MMTMP/RCH2M2MMPRDL001"
tar -C $KERNEL_MODULES/mmngr/mmngr-module/files/ -jcf mmngr-kernel.tar.bz2 .
tar -C $KERNEL_MODULES/mmngrbuf/mmngrbuf-module/files/ -jcf mmngrbuf-kernel.tar.bz2 .
tar -C $KERNEL_MODULES/vspm/vspm-module/files/ -jcf vspm-kernel.tar.bz2 .

USER_MODULES="$MMTMP/RCH2M2MMPRLL001"
tar -C $USER_MODULES/mmngr/mmngr-module/files/ -jcf mmngr.tar.bz2 .
tar -C $USER_MODULES/mmngrbuf/mmngrbuf-module/files/ -jcf mmngrbuf.tar.bz2 .
tar -C $USER_MODULES/vspm/vspm-module/files/ -jcf vspm.tar.bz2 .

rm -rf $MMTMP/*

mv mmngr-kernel.tar.bz2 recipes-kernel/kernel-module-mmngr/files/mmngr.tar.bz2
mv mmngrbuf-kernel.tar.bz2 recipes-kernel/kernel-module-mmngrbuf/files/mmngrbuf.tar.bz2
mv vspm-kernel.tar.bz2 recipes-kernel/kernel-module-vspm/files

mv mmngr.tar.bz2 recipes-multimedia/mmngr-module/files
mv mmngrbuf.tar.bz2 recipes-multimedia/mmngr-module/files
mv vspm.tar.bz2 recipes-multimedia/vspm-module/files/vspm-user.tar.bz2

