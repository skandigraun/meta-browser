DESCRIPTION ?= "Browser made by mozilla"

DEPENDS += "pulseaudio cbindgen-native clang-native \
            nodejs-native python-zstandard gtk+3 \
            libevent dbus-glib"

DEPENDS += '${@bb.utils.contains("PACKAGECONFIG", "disable-sandboxed-libraries", "", "wasi-sdk-native", d)}'

RDEPENDS:${PN} = "libva"
RDEPENDS:${PN}-dev = "dbus"

# use system's nss in case the CPU has no native crypto support (e.g. armv7)
DEPENDS += "${@bb.utils.contains("TUNE_FEATURES", "crypto", "", "nss-3.89", d)}"
RDEPENDS:${PN} += "${@bb.utils.contains("TUNE_FEATURES", "crypto", "", "nss-3.89 nspr", d)}"

LICENSE = "MPL-2.0"

CVE_PRODUCT = "mozilla:firefox"

S = "${WORKDIR}/firefox-${MOZ_APP_BASE_VERSION}"

MOZ_APP_BASE_VERSION = "${@'${PV}'.replace('esr', '')}"

inherit mozilla

TOOLCHAIN:pn-firefox = "clang"
AS = "${CC}"

DISABLE_STATIC=""

ARM_INSTRUCTION_SET:armv5 = "arm"

PACKAGECONFIG ??= "${@bb.utils.contains("DISTRO_FEATURES", "alsa", "alsa", "", d)} \
                   ${@bb.utils.contains("DISTRO_FEATURES", "wayland", "wayland", "", d)} \
                   ${@bb.utils.contains_any("TARGET_ARCH", "x86_64 arm aarch64", "webrtc", "", d)} \
"
PACKAGECONFIG[alsa] = "--enable-alsa,--disable-alsa,alsa-lib"
PACKAGECONFIG[wayland] = "--enable-default-toolkit=cairo-gtk3-wayland,--enable-default-toolkit=cairo-gtk3,virtual/egl,"
PACKAGECONFIG[gpu] = ",,,"
PACKAGECONFIG[openmax] = "--enable-openmax,,,"
PACKAGECONFIG[webgl] = ",,,"
PACKAGECONFIG[webrtc] = "--enable-webrtc,--disable-webrtc,,"
PACKAGECONFIG[forbid-multiple-compositors] = ",,,"
PACKAGECONFIG[disable-sandboxed-libraries] = ",,,"

EXTRA_OECONF += '${@bb.utils.contains("TUNE_FEATURES", "crypto", "", "--with-system-nss", d)}'
EXTRA_OECONF += '${@bb.utils.contains("PACKAGECONFIG", "disable-sandboxed-libraries", "--without-wasm-sandboxed-libraries", "", d)}'

do_compile:prepend(){
    head -n 38 "${WORKDIR}/cargo_home/config" > "${WORKDIR}/cargo_home/config_tmp"
    mv "${WORKDIR}/cargo_home/config_tmp" "${WORKDIR}/cargo_home/config"
}

do_install:append() {
    install -d ${D}${datadir}/applications
    install -d ${D}${datadir}/pixmaps

    install -m 0644 ${WORKDIR}/mozilla-firefox.desktop ${D}${datadir}/applications/
    install -m 0644 ${WORKDIR}/mozilla-firefox.png ${D}${datadir}/pixmaps/
    install -m 0644 ${WORKDIR}/prefs/vendor.js ${D}${libdir}/${PN}/defaults/pref/
    if [ -n "${@bb.utils.contains_any('PACKAGECONFIG', 'gpu', '1', '', d)}" ]; then
        install -m 0644 ${WORKDIR}/prefs/gpu.js ${D}${libdir}/${PN}/defaults/pref/
    fi
    if [ -n "${@bb.utils.contains('PACKAGECONFIG', 'openmax', '1', '', d)}" ]; then
        install -m 0644 ${WORKDIR}/prefs/openmax.js ${D}${libdir}/${PN}/defaults/pref/
    fi
    if [ -n "${@bb.utils.contains('PACKAGECONFIG', 'webgl', '1', '', d)}" ]; then
        install -m 0644 ${WORKDIR}/prefs/webgl.js ${D}${libdir}/${PN}/defaults/pref/
    fi
    if [ -n "${@bb.utils.contains('PACKAGECONFIG', 'forbit-multiple-compositors', '1', '', d)}" ]; then
        install -m 0644 ${WORKDIR}/prefs/single-compositor.js ${D}${libdir}/${PN}/defaults/pref/
    fi

    # Fix ownership of files
    chown root:root -R ${D}${datadir}
    chown root:root -R ${D}${libdir}
}

FILES:${PN} = "${bindir}/${PN} \
               ${datadir}/applications/ \
               ${datadir}/pixmaps/ \
               ${libdir}/${PN}/* \
               ${libdir}/${PN}/.autoreg \
               ${bindir}/defaults \
               ${bindir} \
               ${libdir}/firefox/*"

FILES:${PN}-dev += "${datadir}/idl ${bindir}/${PN}-config ${libdir}/${PN}-devel-*"
FILES:${PN}-staticdev += "${libdir}/${PN}-devel-*/sdk/lib/*.a"
FILES:${PN}-dbg += "${libdir}/${PN}/.debug \
                    ${libdir}/${PN}/*/.debug \
                    ${libdir}/${PN}/*/*/.debug \
                    ${libdir}/${PN}/*/*/*/.debug \
                    ${libdir}/${PN}-devel-*/*/.debug \
                    ${libdir}/${PN}-devel-*/*/*/.debug \
                    ${libdir}/${PN}-devel-*/*/*/*/.debug \
                    ${libdir}/${PN}/fix_linux_stack.py \
                    ${bindir}/.debug"

# We don't build XUL as system shared lib, so we can mark all libs as private
PRIVATE_LIBS = " \
    libmozjs.so \
    libxpcom.so \
    libnspr4.so \
    libxul.so \
    libmozalloc.so \
    libplc4.so \
    libplds4.so \
    liblgpllibs.so \
    libmozgtk.so \
    libmozwayland.so \
    libmozsqlite3.so \
    libclearkey.so \
    libmozavcodec.so \
    libmozavutil.so \
"

# mark libraries also provided by nss as private too
PRIVATE_LIBS += " \
    libfreebl3.so \
    libfreeblpriv3.so \
    libnss3.so \
    libnssckbi.so \
    libsmime3.so \
    libnssutil3.so \
    libnssdbm3.so \
    libssl3.so \
    libsoftokn3.so \
"

CLEANBROKEN = "1"

