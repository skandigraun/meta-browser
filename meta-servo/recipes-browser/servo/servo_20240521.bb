SUMMARY = "Servo, the embeddable, independent, memory-safe, modular, parallel web rendering engine"
DESCRIPTION = "Servo is a prototype web browser engine written in the Rust language."
HOMEPAGE = "https://servo.org"
LICENSE = "MPL-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7f365ee39768c228fc315b749d71ded6"

inherit cargo cargo-update-recipe-crates python3-dir

require servo-common.inc

TOOLCHAIN = "clang"

S = "${WORKDIR}/git"

B = "${S}"

DEPENDS += "crown-native taplo-cli-native fontconfig libx11 libgit2 pkgconfig-native dbus python3 linux-libc-headers"

RDEPENDS:${PN} += "libxkbcommon gstreamer1.0-plugins-base gstreamer1.0-plugins-good gstreamer1.0-plugins-ugly \
                   gstreamer1.0-plugins-bad libtool freetype libxi libunwind libxrandr gperf \
                   expat libxcursor libxmu vulkan-loader libjpeg-turbo zlib harfbuzz ncurses"

CARGO_BUILD_FLAGS = "--offline --target ${RUST_TARGET_SYS}"
CARGO = "crown"

do_compile () {
    # there must be a better way to get either gcc version, or rather, the
    # correct include path
    GCC_VER=`find ${RECIPE_SYSROOT}/usr/include/c++ -maxdepth 1 -type d -name "1*" -exec basename {} \;`

    export AS="${WORKDIR}/wrapper/target-rust-cxx"

    export RUSTFLAGS="${RUSTFLAGS} -L ${RECIPE_SYSROOT}/${libdir}/rustlib/${RUST_HOST_SYS}/lib --sysroot=${RECIPE_SYSROOT}"
    export TARGET_CXXFLAGS="${TARGET_CXXFLAGS} -L ${RECIPE_SYSROOT}/${libdir}/rustlib/${RUST_HOST_SYS}/lib --sysroot=${RECIPE_SYSROOT} \
           -I ${RECIPE_SYSROOT}${includedir}/c++/${GCC_VER}/ -I ${RECIPE_SYSROOT}${includedir}/c++/${GCC_VER}/${TARGET_SYS}"

    UPPERCASE_TARGET=`echo ${RUST_HOST_SYS} | tr 'a-z-' 'A-Z_'`

    export CARGO_TARGET_${UPPERCASE_TARGET}_RUSTFLAGS="${RUSTFLAGS}"
    export CARGO_TARGET_${UPPERCASE_TARGET}_LINKER="${WORKDIR}/wrapper/target-rust-ccld"

    export PYTHONPATH=${RECIPE_SYSROOT}/${PYTHON_SITEPACKAGES_DIR}
    ${S}/mach build --prod --target ${RUST_TARGET_SYS}
}

do_install () {
    install -Dm 0755 ${B}/target/${RUST_TARGET_SYS}/production/servo ${D}${base_libdir}/servo/servo
    install -d ${D}${base_libdir}/servo/resources

    cd ${B}/resources
    find . -type d -exec install -d ${D}${base_libdir}/servo/resources/{} \;
    find . -type f -exec install -m 0644 {} ${D}${base_libdir}/servo/resources/{} \;

    install -d ${D}${base_bindir}
    ln -s ${base_libdir}/servo/servo ${D}${base_bindir}/servo
}
