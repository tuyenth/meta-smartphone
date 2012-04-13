require cornucopia.inc
inherit fso-plugin

DEPENDS += "alsa-lib libfsoresource android-rpc libfsosystem"
RPROVIDES_${PN} = "openmoko-alsa-scenarios virtual/alsa-scenarios"
SRCREV = "${FSO_CORNUCOPIA_SRCREV}"
PV = "0.11.0+gitr${SRCPV}"
PE = "2"
PR = "${INC_PR}.0"

EXTRA_OECONF = "\
  --enable-kernel26-rfkill \
  --disable-player-canberra \
  --enable-htcdream-powercontrol \
"

inherit update-rc.d

INITSCRIPT_NAME = "${PN}"
INITSCRIPT_PARAMS = "defaults 27"

inherit systemd
SYSTEMD_PACKAGES = "${PN}-systemd"
SYSTEMD_SERVICE = "${PN}.service"

SRC_URI += "file://${PN}"

do_install_append() {
	install -d ${D}${sysconfdir}/init.d/
	install -m 0755 ${WORKDIR}/${PN} ${D}${sysconfdir}/init.d/
	install -d ${D}${systemd_unitdir}/system/
	install -m 0644 ${S}/data/${PN}.service ${D}${systemd_unitdir}/system/${PN}.service
}

pkg_preinst_${PN}-config () {
	# work-arround for opkg complaining that it cannot replace alsa-default dir (leftover from older fsodeviced) with new alsa-default symlink
        rm -rf ${sysconfdir}/freesmartphone/conf/openmoko_gta/alsa-default/
}

# package modules with extra dependencies in extra packages RDEPENDed by config package

PACKAGES =+ "${PN}-config"
FILES_${PN}-config = "${sysconfdir}/freesmartphone/"
CONFFILES_${PN}-config = " \
  ${sysconfdir}/freesmartphone/conf/openmoko_gta/${PN}.conf \
  ${sysconfdir}/freesmartphone/conf/palm_pre/${PN}.conf \
  ${sysconfdir}/freesmartphone/conf/htc_qualcomm_dream/${PN}.conf \
  ${sysconfdir}/freesmartphone/conf/htc_qualcomm_msm/${PN}.conf \
  ${sysconfdir}/freesmartphone/conf/motorola_ezx/${PN}.conf \
"
RRECOMMENDS_${PN} += "${PN}-config"
RDEPENDS_${PN}-config += "${PN}-modules"

PACKAGES =+ "${PN}-module-accelerometer-kxsd9 ${PN}-module-accelerometer-kxsd9-dev ${PN}-module-accelerometer-kxsd9-dbg"
FILES_${PN}-module-accelerometer-kxsd9 = "${CORNUCOPIA_MODULE_DIR}/accelerometer_kxsd9.so"
FILES_${PN}-module-accelerometer-kxsd9-dev = "${CORNUCOPIA_MODULE_DIR}/accelerometer_kxsd9.la"
FILES_${PN}-module-accelerometer-kxsd9-dbg = "${CORNUCOPIA_MODULE_DIR}/.debug/accelerometer_kxsd9*"

PACKAGES =+ "${PN}-module-palmpre-quirks ${PN}-module-palmpre-quirks-dev ${PN}-module-palmpre-quirks-dbg"
FILES_${PN}-module-palmpre-quirks = "${CORNUCOPIA_MODULE_DIR}/palmpre_quirks.so"
FILES_${PN}-module-palmpre-quirks-dev = "${CORNUCOPIA_MODULE_DIR}/palmpre_quirks.la"
FILES_${PN}-module-palmpre-quirks-dbg = "${CORNUCOPIA_MODULE_DIR}/.debug/palmpre_quirks*"

PACKAGES =+ "${PN}-module-backlight-omappanel ${PN}-module-backlight-omappanel-dev ${PN}-module-backlight-omappanel-dbg"
FILES_${PN}-module-backlight-omappanel = "${CORNUCOPIA_MODULE_DIR}/backlight_omappanel.so"
FILES_${PN}-module-backlight-omappanel-dev = "${CORNUCOPIA_MODULE_DIR}/backlight_omappanel.la"
FILES_${PN}-module-backlight-omappanel-dbg = "${CORNUCOPIA_MODULE_DIR}/.debug/backlight_omappanel*"

PACKAGES =+ "${PN}-module-vibrator-omapvibe ${PN}-module-vibrator-omapvibe-dev ${PN}-module-vibrator-omapvibe-dbg"
FILES_${PN}-module-vibrator-omapvibe = "${CORNUCOPIA_MODULE_DIR}/vibrator_omapvibe.so"
FILES_${PN}-module-vibrator-omapvibe-dev = "${CORNUCOPIA_MODULE_DIR}/vibrator_omapvibe.la"
FILES_${PN}-module-vibrator-omapvibe-dbg = "${CORNUCOPIA_MODULE_DIR}/.debug/vibrator_omapvibe*"

PACKAGES =+ "${PN}-module-accelerometer-lis302 ${PN}-module-accelerometer-lis302-dev ${PN}-module-accelerometer-lis302-dbg"
FILES_${PN}-module-accelerometer-lis302 = "${CORNUCOPIA_MODULE_DIR}/accelerometer_lis302.so"
FILES_${PN}-module-accelerometer-lis302-dev = "${CORNUCOPIA_MODULE_DIR}/accelerometer_lis302.la"
FILES_${PN}-module-accelerometer-lis302-dbg = "${CORNUCOPIA_MODULE_DIR}/.debug/accelerometer_lis302*"

PACKAGES =+ "${PN}-module-openmoko-powercontrol ${PN}-module-openmoko-powercontrol-dev ${PN}-module-openmoko-powercontrol-dbg"
FILES_${PN}-module-openmoko-powercontrol = "${CORNUCOPIA_MODULE_DIR}/openmoko_powercontrol.so"
FILES_${PN}-module-openmoko-powercontrol-dev = "${CORNUCOPIA_MODULE_DIR}/openmoko_powercontrol.la"
FILES_${PN}-module-openmoko-powercontrol-dbg = "${CORNUCOPIA_MODULE_DIR}/.debug/openmoko_powercontrol*"

PACKAGES =+ "${PN}-module-ambientlight-n900 ${PN}-module-ambientlight-n900-dev ${PN}-module-ambientlight-n900-dbg"
FILES_${PN}-module-ambientlight-n900 = "${CORNUCOPIA_MODULE_DIR}/ambientlight_n900.so"
FILES_${PN}-module-ambientlight-n900-dev = "${CORNUCOPIA_MODULE_DIR}/ambientlight_n900.la"
FILES_${PN}-module-ambientlight-n900-dbg = "${CORNUCOPIA_MODULE_DIR}/.debug/ambientlight_n900*"

PACKAGES =+ "${PN}-module-powersupply-n900 ${PN}-module-powersupply-n900-dev ${PN}-module-powersupply-n900-dbg"
FILES_${PN}-module-powersupply-n900 = "${CORNUCOPIA_MODULE_DIR}/powersupply_n900.so"
FILES_${PN}-module-powersupply-n900-dev = "${CORNUCOPIA_MODULE_DIR}/powersupply_n900.la"
FILES_${PN}-module-powersupply-n900-dbg = "${CORNUCOPIA_MODULE_DIR}/.debug/powersupply_n900*"

PACKAGES =+ "${PN}-module-proximity-n900 ${PN}-module-proximity-n900-dev ${PN}-module-proximity-n900-dbg"
FILES_${PN}-module-proximity-n900 = "${CORNUCOPIA_MODULE_DIR}/proximity_n900.so"
FILES_${PN}-module-proximity-n900-dev = "${CORNUCOPIA_MODULE_DIR}/proximity_n900.la"
FILES_${PN}-module-proximity-n900-dbg = "${CORNUCOPIA_MODULE_DIR}/.debug/proximity_n900*"

PACKAGES =+ "${PN}-module-n900-powercontrol ${PN}-module-n900-powercontrol-dev ${PN}-module-n900-powercontrol-dbg"
FILES_${PN}-module-n900-powercontrol = "${CORNUCOPIA_MODULE_DIR}/n900_powercontrol.so"
FILES_${PN}-module-n900-powercontrol-dev = "${CORNUCOPIA_MODULE_DIR}/n900_powercontrol.la"
FILES_${PN}-module-n900-powercontrol-dbg = "${CORNUCOPIA_MODULE_DIR}/.debug/n900_powercontrol*"

PACKAGES =+ "${PN}-module-router-qdsp5 ${PN}-module-router-qdsp5-dev ${PN}-module-router-qdsp5-dbg"
FILES_${PN}-module-router-qdsp5 = "${CORNUCOPIA_MODULE_DIR}/router_qdsp5.so"
FILES_${PN}-module-router-qdsp5-dev = "${CORNUCOPIA_MODULE_DIR}/router_qdsp5.la"
FILES_${PN}-module-router-qdsp5-dbg = "${CORNUCOPIA_MODULE_DIR}/.debug/router_qdsp5*"

PACKAGES =+ "${PN}-module-vibrator-timedoutputclass ${PN}-module-vibrator-timedoutputclass-dev ${PN}-module-vibrator-timedoutputclass-dbg"
FILES_${PN}-module-vibrator-timedoutputclass = "${CORNUCOPIA_MODULE_DIR}/vibrator_timedoutputclass.so"
FILES_${PN}-module-vibrator-timedoutputclass-dev = "${CORNUCOPIA_MODULE_DIR}/vibrator_timedoutputclass.la"
FILES_${PN}-module-vibrator-timedoutputclass-dbg = "${CORNUCOPIA_MODULE_DIR}/.debug/vibrator_timedoutputclass*"

## shared modules or modules without known OE machine to RDEPEND on them (so kept in main module for now)
#  accelerometer.so
#  audio.so
#  dummy_input.so
#  gpio_input.so
#  kernel26_cpufreq.so
#  kernel26_display.so
#  kernel26_firmwareloader.so
#  kernel26_leds.so
#  kernel26_powersupply.so
#  kernel26_rfkill.so
#  kernel26_rtc.so
#  kernel_idle.so
#  kernel_info.so
#  kernel_input.so
#  player_alsa.so
#  powercontrol_ifconfig.so
#  router_alsa.so
#  thinkpad_powercontrol.so
#  vibrator_ledclass.so
