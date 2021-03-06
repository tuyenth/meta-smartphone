require ${BPN}.inc

# isn't compatible with current default libsamsung-ipc_0.2.0, let fsogsmd_git to be used by default
DEFAULT_PREFERENCE = "-2"

PR = "${INC_PR}.0"

SRC_URI += "file://0001-fsogsmd-remove-global-theServiceDependencies-variabl.patch"
SRC_URI += "file://0001-fsogsmd-upgrade-to-libgee-0.8.patch"

SRC_URI[md5sum] = "49fbda557d08c838ce60f8dd4d117ec8"
SRC_URI[sha256sum] = "466899105f1b535359fdfaac49ad190f186b0f9221a8fdbe39350f9dd5492e66"

PNBLACKLIST[fsogsmd] ?= "Depends on blacklisted libfso-glib - the recipe will be removed on 2017-09-01 unless the issue is fixed"
