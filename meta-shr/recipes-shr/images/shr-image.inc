#------------------------------------------------------
# SHR Image Recipe
#------------------------------------------------------

PV = "2.0"
PR = "r20"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

DEPENDS += "packagegroup-shr-minimal"

IMAGE_FEATURES += "debug-tweaks package-management ssh-server-openssh"

inherit core-image

# set sane default for the SPLASH variable
SPLASH ?= ""

# some default locales
# IMAGE_LINGUAS ?= "de-de fr-fr en-gb"
IMAGE_LINGUAS ?= "en-us"

RDEPENDS_${PN} += "\
  packagegroup-shr-minimal-base \
  packagegroup-shr-minimal-cli \
  packagegroup-shr-minimal-fso \
  packagegroup-shr-minimal-apps \
  packagegroup-shr-minimal-audio \
  packagegroup-shr-minimal-gtk \
  packagegroup-shr-minimal-x \
"

IMAGE_INSTALL += "\
  packagegroup-shr-minimal-base \
  packagegroup-shr-minimal-cli \
  packagegroup-shr-minimal-fso \
  packagegroup-shr-minimal-apps \
  packagegroup-shr-minimal-audio \
  packagegroup-shr-minimal-gtk \
  packagegroup-shr-minimal-x \
"

# Make graphical.target the default instead of multi-user.target
SYSTEMD_DEFAULT_TARGET = "graphical.target"

shr_rootfs_gta02_postprocess() {
    curdir=$PWD
    cd ${IMAGE_ROOTFS}/boot
    ln -s uImage uImage-GTA02.bin
    echo 'loglevel=1 quiet splash' > append-GTA02
    cd $curdir
}

shr_rootfs_gta01_postprocess() {
    curdir=$PWD
    cd ${IMAGE_ROOTFS}/boot
    ln -s uImage uImage-GTA01.bin
    echo 'loglevel=1 quiet splash' > append-GTA01
    cd $curdir
}

# We don't want /boot directory in jffs2/ubi/ubifs images on gta01
IMAGE_CMD_jffs2_om-gta01 = "rm -rf ${IMAGE_ROOTFS}-boot; mv ${IMAGE_ROOTFS}/boot ${IMAGE_ROOTFS}-boot && mkfs.jffs2 -x lzo --root=${IMAGE_ROOTFS} --faketime --output=${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.jffs2 ${EXTRA_IMAGECMD}; mv ${IMAGE_ROOTFS}-boot ${IMAGE_ROOTFS}/boot"
IMAGE_CMD_ubi_om-gta01 = "rm -rf ${IMAGE_ROOTFS}-boot; mv ${IMAGE_ROOTFS}/boot ${IMAGE_ROOTFS}-boot && echo \[ubifs\] > ubinize.cfg ; echo mode=ubi >> ubinize.cfg ; echo image=${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.ubifs >> ubinize.cfg ; echo vol_id=0 >> ubinize.cfg ; echo vol_type=dynamic >> ubinize.cfg ; echo vol_name=${UBI_VOLNAME} >> ubinize.cfg ; echo vol_flags=autoresize >> ubinize.cfg;mkfs.ubifs -r ${IMAGE_ROOTFS} -o ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.ubifs ${MKUBIFS_ARGS} && ubinize -o ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.rootfs.ubi ${UBINIZE_ARGS} ubinize.cfg; mv ${IMAGE_ROOTFS}-boot ${IMAGE_ROOTFS}/boot"
IMAGE_CMD_ubifs_om-gta01 = "rm -rf ${IMAGE_ROOTFS}-boot; mv ${IMAGE_ROOTFS}/boot ${IMAGE_ROOTFS}-boot && mkfs.ubifs -r ${IMAGE_ROOTFS} -o ${DEPLOY_DIR_IMAGE}/${IMAGE_NAME}.ubifs.img ${MKUBIFS_ARGS}; mv ${IMAGE_ROOTFS}-boot ${IMAGE_ROOTFS}/boot"

ROOTFS_POSTPROCESS_COMMAND += " rootfs_update_timestamp;"
#ROOTFS_POSTPROCESS_COMMAND += " install_linguas;"

ROOTFS_POSTPROCESS_COMMAND_append_om-gta02 = " shr_rootfs_gta02_postprocess;"
ROOTFS_POSTPROCESS_COMMAND_append_om-gta01 = " shr_rootfs_gta01_postprocess;"
