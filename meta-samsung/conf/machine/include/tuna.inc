#-----------------------------------------------------------------------------
#@TYPE: Machine
#@NAME: Samsung Tuna (also known as Galaxy Nexus)
#@DESCRIPTION: Machine configuration for the Samsung Tuna
#-----------------------------------------------------------------------------

TARGET_ARCH = "arm"

# use this for overrides
MACHINE_CLASS = "tuna"

PREFERRED_PROVIDER_virtual/kernel ?= "linux-samsung-tuna"
MACHINE_KERNEL_PR = "r12"

MACHINE_FEATURES = "kernel26 apm alsa bluetooth gps usbgadget usbhost phone wifi vfat ext2 keyboard"
MACHINE_DISPLAY_WIDTH_PIXELS = "480"
MACHINE_DISPLAY_HEIGHT_PIXELS = "800"
MACHINE_DISPLAY_ORIENTATION = "0"
MACHINE_DISPLAY_PPI = "233"

PREFERRED_PROVIDER_virtual/egl ?= "libhybris"
PREFERRED_PROVIDER_virtual/libgles1 ?= "libhybris"
PREFERRED_PROVIDER_virtual/libgles2 ?= "libhybris"

PREFERRED_PROVIDER_virtual/xserver = "xserver-xorg"
XSERVER = " \
    xserver-xorg \
    xf86-input-evdev \
    xf86-video-omapfb \
"

# We need the PVR module here
MACHINE_EXTRA_RDEPENDS = " \
    kernel-module-pvrsrvkm-sgx540-120 \
"

KERNEL_IMAGETYPE = "zImage"
IMAGE_FSTYPES += "tar.gz ext4"
IMAGE_ROOTFS_EXTRA_SPACE = "200000"

ANDROID_BOARD_BOOTIMAGE_PARTITION = "/dev/block/mmcblk0p7"
ANDROID_BOARD_SYSTEMIMAGE_PARTITION_SIZE = "685768704"
ANDROID_BOARD_FLASH_BLOCK_SIZE = "4096"

SERIAL_CONSOLE = "115200 ttyFIQ0"

require conf/machine/include/tune-cortexa8.inc
# same as conf/machine/include/tune-cortexa9.inc but without including
# conf/machine/include/arm/arch-armv7a.inc twice
TUNEVALID[cortexa9] = "Enable Cortex-A9 specific processor optimizations"
TUNE_CCARGS .= "${@bb.utils.contains("TUNE_FEATURES", "cortexa9", " -mtune=cortex-a9", "", d)}"

# Little Endian base configs
AVAILTUNES += "cortexa9 cortexa9t cortexa9-neon cortexa9t-neon"
ARMPKGARCH_tune-cortexa9 = "cortexa9"
ARMPKGARCH_tune-cortexa9t = "cortexa9"
ARMPKGARCH_tune-cortexa9-neon = "cortexa9"
ARMPKGARCH_tune-cortexa9t-neon = "cortexa9"
TUNE_FEATURES_tune-cortexa9 = "${TUNE_FEATURES_tune-armv7a} cortexa9"
TUNE_FEATURES_tune-cortexa9t = "${TUNE_FEATURES_tune-armv7at} cortexa9"
TUNE_FEATURES_tune-cortexa9-neon = "${TUNE_FEATURES_tune-armv7a-neon} cortexa9"
TUNE_FEATURES_tune-cortexa9t-neon = "${TUNE_FEATURES_tune-armv7at-neon} cortexa9"
PACKAGE_EXTRA_ARCHS_tune-cortexa9 = "${PACKAGE_EXTRA_ARCHS_tune-armv7a} cortexa9-vfp"
PACKAGE_EXTRA_ARCHS_tune-cortexa9t = "${PACKAGE_EXTRA_ARCHS_tune-armv7at} cortexa9-vfp cortexa9t2-vfp"
PACKAGE_EXTRA_ARCHS_tune-cortexa9-neon = "${PACKAGE_EXTRA_ARCHS_tune-armv7a-neon} cortexa9-vfp cortexa9-vfp-neon"
PACKAGE_EXTRA_ARCHS_tune-cortexa9t-neon = "${PACKAGE_EXTRA_ARCHS_tune-armv7at-neon} cortexa9-vfp cortexa9-vfp-neon cortexa9t2-vfp cortexa9t2-vfp-neon"

# VFP Tunes
AVAILTUNES += "cortexa9hf cortexa9thf cortexa9hf-neon cortexa9thf-neon"
ARMPKGARCH_tune-cortexa9hf = "cortexa9"
ARMPKGARCH_tune-cortexa9thf = "cortexa9"
ARMPKGARCH_tune-cortexa9hf-neon = "cortexa9"
ARMPKGARCH_tune-cortexa9thf-neon = "cortexa9"
TUNE_FEATURES_tune-cortexa9hf ?= "${TUNE_FEATURES_tune-armv7ahf} cortexa9"
TUNE_FEATURES_tune-cortexa9thf ?= "${TUNE_FEATURES_tune-armv7athf} cortexa9"
TUNE_FEATURES_tune-cortexa9hf-neon ?= "${TUNE_FEATURES_tune-armv7ahf-neon} cortexa9"
TUNE_FEATURES_tune-cortexa9thf-neon ?= "${TUNE_FEATURES_tune-armv7ahf-neon} cortexa9"
PACKAGE_EXTRA_ARCHS_tune-cortexa9hf = "${PACKAGE_EXTRA_ARCHS_tune-armv7ahf} cortexa9hf-vfp"
PACKAGE_EXTRA_ARCHS_tune-cortexa9thf = "${PACKAGE_EXTRA_ARCHS_tune-armv7athf} cortexa9hf-vfp cortexa9t2hf-vfp"
PACKAGE_EXTRA_ARCHS_tune-cortexa9hf-neon = "${PACKAGE_EXTRA_ARCHS_tune-armv7ahf-neon} cortexa9hf-vfp cortexa9hf-vfp-neon"
PACKAGE_EXTRA_ARCHS_tune-cortexa9thf-neon = "${PACKAGE_EXTRA_ARCHS_tune-armv7athf-neon} cortexa9hf-vfp cortexa9hf-vfp-neon cortexa9t2-vfp cortexa9t2hf-vfp-neon"
