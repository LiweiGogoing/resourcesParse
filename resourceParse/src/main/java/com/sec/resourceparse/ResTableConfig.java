package com.sec.resourceparse;

import java.util.Arrays;

/**
 struct ResTable_config
        {
        // Number of bytes in this structure.
        uint32_t size;

        union {
        struct {
        // Mobile country code (from SIM).  0 means "any".
        uint16_t mcc;
        // Mobile network code (from SIM).  0 means "any".
        uint16_t mnc;
        };
        uint32_t imsi;
        };

        union {
        struct {
        // This field can take three different forms:
        // - \0\0 means "any".
        //
        // - Two 7 bit ascii values interpreted as ISO-639-1 language
        //   codes ('fr', 'en' etc. etc.). The high bit for both bytes is
        //   zero.
        //
        // - A single 16 bit little endian packed value representing an
        //   ISO-639-2 3 letter language code. This will be of the form:
        //
        //   {1, t, t, t, t, t, s, s, s, s, s, f, f, f, f, f}
        //
        //   bit[0, 4] = first letter of the language code
        //   bit[5, 9] = second letter of the language code
        //   bit[10, 14] = third letter of the language code.
        //   bit[15] = 1 always
        //
        // For backwards compatibility, languages that have unambiguous
        // two letter codes are represented in that format.
        //
        // The layout is always bigendian irrespective of the runtime
        // architecture.
        char language[2];

        // This field can take three different forms:
        // - \0\0 means "any".
        //
        // - Two 7 bit ascii values interpreted as 2 letter region
        //   codes ('US', 'GB' etc.). The high bit for both bytes is zero.
        //
        // - An UN M.49 3 digit region code. For simplicity, these are packed
        //   in the same manner as the language codes, though we should need
        //   only 10 bits to represent them, instead of the 15.
        //
        // The layout is always bigendian irrespective of the runtime
        // architecture.
        char country[2];
        };
        uint32_t locale;
        };

enum {
        ORIENTATION_ANY  = ACONFIGURATION_ORIENTATION_ANY,
        ORIENTATION_PORT = ACONFIGURATION_ORIENTATION_PORT,
        ORIENTATION_LAND = ACONFIGURATION_ORIENTATION_LAND,
        ORIENTATION_SQUARE = ACONFIGURATION_ORIENTATION_SQUARE,
        };

enum {
        TOUCHSCREEN_ANY  = ACONFIGURATION_TOUCHSCREEN_ANY,
        TOUCHSCREEN_NOTOUCH  = ACONFIGURATION_TOUCHSCREEN_NOTOUCH,
        TOUCHSCREEN_STYLUS  = ACONFIGURATION_TOUCHSCREEN_STYLUS,
        TOUCHSCREEN_FINGER  = ACONFIGURATION_TOUCHSCREEN_FINGER,
        };

enum {
        DENSITY_DEFAULT = ACONFIGURATION_DENSITY_DEFAULT,
        DENSITY_LOW = ACONFIGURATION_DENSITY_LOW,
        DENSITY_MEDIUM = ACONFIGURATION_DENSITY_MEDIUM,
        DENSITY_TV = ACONFIGURATION_DENSITY_TV,
        DENSITY_HIGH = ACONFIGURATION_DENSITY_HIGH,
        DENSITY_XHIGH = ACONFIGURATION_DENSITY_XHIGH,
        DENSITY_XXHIGH = ACONFIGURATION_DENSITY_XXHIGH,
        DENSITY_XXXHIGH = ACONFIGURATION_DENSITY_XXXHIGH,
        DENSITY_ANY = ACONFIGURATION_DENSITY_ANY,
        DENSITY_NONE = ACONFIGURATION_DENSITY_NONE
        };

        union {
        struct {
        uint8_t orientation;
        uint8_t touchscreen;
        uint16_t density;
        };
        uint32_t screenType;
        };

enum {
        KEYBOARD_ANY  = ACONFIGURATION_KEYBOARD_ANY,
        KEYBOARD_NOKEYS  = ACONFIGURATION_KEYBOARD_NOKEYS,
        KEYBOARD_QWERTY  = ACONFIGURATION_KEYBOARD_QWERTY,
        KEYBOARD_12KEY  = ACONFIGURATION_KEYBOARD_12KEY,
        };

enum {
        NAVIGATION_ANY  = ACONFIGURATION_NAVIGATION_ANY,
        NAVIGATION_NONAV  = ACONFIGURATION_NAVIGATION_NONAV,
        NAVIGATION_DPAD  = ACONFIGURATION_NAVIGATION_DPAD,
        NAVIGATION_TRACKBALL  = ACONFIGURATION_NAVIGATION_TRACKBALL,
        NAVIGATION_WHEEL  = ACONFIGURATION_NAVIGATION_WHEEL,
        };

enum {
        MASK_KEYSHIDDEN = 0x0003,
        KEYSHIDDEN_ANY = ACONFIGURATION_KEYSHIDDEN_ANY,
        KEYSHIDDEN_NO = ACONFIGURATION_KEYSHIDDEN_NO,
        KEYSHIDDEN_YES = ACONFIGURATION_KEYSHIDDEN_YES,
        KEYSHIDDEN_SOFT = ACONFIGURATION_KEYSHIDDEN_SOFT,
        };

enum {
        MASK_NAVHIDDEN = 0x000c,
        SHIFT_NAVHIDDEN = 2,
        NAVHIDDEN_ANY = ACONFIGURATION_NAVHIDDEN_ANY << SHIFT_NAVHIDDEN,
        NAVHIDDEN_NO = ACONFIGURATION_NAVHIDDEN_NO << SHIFT_NAVHIDDEN,
        NAVHIDDEN_YES = ACONFIGURATION_NAVHIDDEN_YES << SHIFT_NAVHIDDEN,
        };

        union {
        struct {
        uint8_t keyboard;
        uint8_t navigation;
        uint8_t inputFlags;
        uint8_t inputPad0;
        };
        uint32_t input;
        };

enum {
        SCREENWIDTH_ANY = 0
        };

enum {
        SCREENHEIGHT_ANY = 0
        };

        union {
        struct {
        uint16_t screenWidth;
        uint16_t screenHeight;
        };
        uint32_t screenSize;
        };

enum {
        SDKVERSION_ANY = 0
        };

enum {
        MINORVERSION_ANY = 0
        };

        union {
        struct {
        uint16_t sdkVersion;
        // For now minorVersion must always be 0!!!  Its meaning
        // is currently undefined.
        uint16_t minorVersion;
        };
        uint32_t version;
        };

enum {
        // screenLayout bits for screen size class.
        MASK_SCREENSIZE = 0x0f,
        SCREENSIZE_ANY = ACONFIGURATION_SCREENSIZE_ANY,
        SCREENSIZE_SMALL = ACONFIGURATION_SCREENSIZE_SMALL,
        SCREENSIZE_NORMAL = ACONFIGURATION_SCREENSIZE_NORMAL,
        SCREENSIZE_LARGE = ACONFIGURATION_SCREENSIZE_LARGE,
        SCREENSIZE_XLARGE = ACONFIGURATION_SCREENSIZE_XLARGE,

        // screenLayout bits for wide/long screen variation.
        MASK_SCREENLONG = 0x30,
        SHIFT_SCREENLONG = 4,
        SCREENLONG_ANY = ACONFIGURATION_SCREENLONG_ANY << SHIFT_SCREENLONG,
        SCREENLONG_NO = ACONFIGURATION_SCREENLONG_NO << SHIFT_SCREENLONG,
        SCREENLONG_YES = ACONFIGURATION_SCREENLONG_YES << SHIFT_SCREENLONG,

        // screenLayout bits for layout direction.
        MASK_LAYOUTDIR = 0xC0,
        SHIFT_LAYOUTDIR = 6,
        LAYOUTDIR_ANY = ACONFIGURATION_LAYOUTDIR_ANY << SHIFT_LAYOUTDIR,
        LAYOUTDIR_LTR = ACONFIGURATION_LAYOUTDIR_LTR << SHIFT_LAYOUTDIR,
        LAYOUTDIR_RTL = ACONFIGURATION_LAYOUTDIR_RTL << SHIFT_LAYOUTDIR,
        };

enum {
        // uiMode bits for the mode type.
        MASK_UI_MODE_TYPE = 0x0f,
        UI_MODE_TYPE_ANY = ACONFIGURATION_UI_MODE_TYPE_ANY,
        UI_MODE_TYPE_NORMAL = ACONFIGURATION_UI_MODE_TYPE_NORMAL,
        UI_MODE_TYPE_DESK = ACONFIGURATION_UI_MODE_TYPE_DESK,
        UI_MODE_TYPE_CAR = ACONFIGURATION_UI_MODE_TYPE_CAR,
        UI_MODE_TYPE_TELEVISION = ACONFIGURATION_UI_MODE_TYPE_TELEVISION,
        UI_MODE_TYPE_APPLIANCE = ACONFIGURATION_UI_MODE_TYPE_APPLIANCE,
        UI_MODE_TYPE_WATCH = ACONFIGURATION_UI_MODE_TYPE_WATCH,
        UI_MODE_TYPE_VR_HEADSET = ACONFIGURATION_UI_MODE_TYPE_VR_HEADSET,

        // uiMode bits for the night switch.
        MASK_UI_MODE_NIGHT = 0x30,
        SHIFT_UI_MODE_NIGHT = 4,
        UI_MODE_NIGHT_ANY = ACONFIGURATION_UI_MODE_NIGHT_ANY << SHIFT_UI_MODE_NIGHT,
        UI_MODE_NIGHT_NO = ACONFIGURATION_UI_MODE_NIGHT_NO << SHIFT_UI_MODE_NIGHT,
        UI_MODE_NIGHT_YES = ACONFIGURATION_UI_MODE_NIGHT_YES << SHIFT_UI_MODE_NIGHT,
        };

        union {
        struct {
        uint8_t screenLayout;
        uint8_t uiMode;
        uint16_t smallestScreenWidthDp;
        };
        uint32_t screenConfig;
        };

        union {
        struct {
        uint16_t screenWidthDp;
        uint16_t screenHeightDp;
        };
        uint32_t screenSizeDp;
        };

        // The ISO-15924 short name for the script corresponding to this
        // configuration. (eg. Hant, Latn, etc.). Interpreted in conjunction with
        // the locale field.
        char localeScript[4];

        // A single BCP-47 variant subtag. Will vary in length between 4 and 8
        // chars. Interpreted in conjunction with the locale field.
        char localeVariant[8];

enum {
        // screenLayout2 bits for round/notround.
        MASK_SCREENROUND = 0x03,
        SCREENROUND_ANY = ACONFIGURATION_SCREENROUND_ANY,
        SCREENROUND_NO = ACONFIGURATION_SCREENROUND_NO,
        SCREENROUND_YES = ACONFIGURATION_SCREENROUND_YES,
        };

enum {
        // colorMode bits for wide-color gamut/narrow-color gamut.
        MASK_WIDE_COLOR_GAMUT = 0x03,
        WIDE_COLOR_GAMUT_ANY = ACONFIGURATION_WIDE_COLOR_GAMUT_ANY,
        WIDE_COLOR_GAMUT_NO = ACONFIGURATION_WIDE_COLOR_GAMUT_NO,
        WIDE_COLOR_GAMUT_YES = ACONFIGURATION_WIDE_COLOR_GAMUT_YES,

        // colorMode bits for HDR/LDR.
        MASK_HDR = 0x0c,
        SHIFT_COLOR_MODE_HDR = 2,
        HDR_ANY = ACONFIGURATION_HDR_ANY << SHIFT_COLOR_MODE_HDR,
        HDR_NO = ACONFIGURATION_HDR_NO << SHIFT_COLOR_MODE_HDR,
        HDR_YES = ACONFIGURATION_HDR_YES << SHIFT_COLOR_MODE_HDR,
        };

        // An extension of screenConfig.
        union {
        struct {
        uint8_t screenLayout2;      // Contains round/notround qualifier.
        uint8_t colorMode;          // Wide-gamut, HDR, etc.
        uint16_t screenConfigPad2;  // Reserved padding.
        };
        uint32_t screenConfig2;
        };

        // If false and localeScript is set, it means that the script of the locale
        // was explicitly provided.
        //
        // If true, it means that localeScript was automatically computed.
        // localeScript may still not be set in this case, which means that we
        // tried but could not compute a script.
        bool localeScriptWasComputed;

        // The value of BCP 47 Unicode extension for key 'nu' (numbering system).
        // Varies in length from 3 to 8 chars. Zero-filled value.
        char localeNumberingSystem[8];

        void copyFromDeviceNoSwap(const ResTable_config& o);

        void copyFromDtoH(const ResTable_config& o);

        void swapHtoD();

        int compare(const ResTable_config& o) const;
        int compareLogical(const ResTable_config& o) const;

        inline bool operator<(const ResTable_config& o) const { return compare(o) < 0; }

// Flags indicating a set of config values.  These flag constants must
// match the corresponding ones in android.content.pm.ActivityInfo and
// attrs_manifest.xml.
enum {
        CONFIG_MCC = ACONFIGURATION_MCC,
        CONFIG_MNC = ACONFIGURATION_MNC,
        CONFIG_LOCALE = ACONFIGURATION_LOCALE,
        CONFIG_TOUCHSCREEN = ACONFIGURATION_TOUCHSCREEN,
        CONFIG_KEYBOARD = ACONFIGURATION_KEYBOARD,
        CONFIG_KEYBOARD_HIDDEN = ACONFIGURATION_KEYBOARD_HIDDEN,
        CONFIG_NAVIGATION = ACONFIGURATION_NAVIGATION,
        CONFIG_ORIENTATION = ACONFIGURATION_ORIENTATION,
        CONFIG_DENSITY = ACONFIGURATION_DENSITY,
        CONFIG_SCREEN_SIZE = ACONFIGURATION_SCREEN_SIZE,
        CONFIG_SMALLEST_SCREEN_SIZE = ACONFIGURATION_SMALLEST_SCREEN_SIZE,
        CONFIG_VERSION = ACONFIGURATION_VERSION,
        CONFIG_SCREEN_LAYOUT = ACONFIGURATION_SCREEN_LAYOUT,
        CONFIG_UI_MODE = ACONFIGURATION_UI_MODE,
        CONFIG_LAYOUTDIR = ACONFIGURATION_LAYOUTDIR,
        CONFIG_SCREEN_ROUND = ACONFIGURATION_SCREEN_ROUND,
        CONFIG_COLOR_MODE = ACONFIGURATION_COLOR_MODE,
        };

        // Compare two configuration, returning CONFIG_* flags set for each value
        // that is different.
        int diff(const ResTable_config& o) const;

        // Return true if 'this' is more specific than 'o'.
        bool isMoreSpecificThan(const ResTable_config& o) const;

        // Return true if 'this' is a better match than 'o' for the 'requested'
        // configuration.  This assumes that match() has already been used to
        // remove any configurations that don't match the requested configuration
        // at all; if they are not first filtered, non-matching results can be
        // considered better than matching ones.
        // The general rule per attribute: if the request cares about an attribute
        // (it normally does), if the two (this and o) are equal it's a tie.  If
        // they are not equal then one must be generic because only generic and
        // '==requested' will pass the match() call.  So if this is not generic,
        // it wins.  If this IS generic, o wins (return false).
        bool isBetterThan(const ResTable_config& o, const ResTable_config* requested) const;

        // Return true if 'this' can be considered a match for the parameters in
        // 'settings'.
        // Note this is asymetric.  A default piece of data will match every request
        // but a request for the default should not match odd specifics
        // (ie, request with no mcc should not match a particular mcc's data)
        // settings is the requested settings
        bool match(const ResTable_config& settings) const;

        // Get the string representation of the locale component of this
        // Config. The maximum size of this representation will be
        // |RESTABLE_MAX_LOCALE_LEN| (including a terminating '\0').
        //
        // Example: en-US, en-Latn-US, en-POSIX.
        //
        // If canonicalize is set, Tagalog (tl) locales get converted
        // to Filipino (fil).
        void getBcp47Locale(char* out, bool canonicalize=false) const;

        // Append to str the resource-qualifer string representation of the
        // locale component of this Config. If the locale is only country
        // and language, it will look like en-rUS. If it has scripts and
        // variants, it will be a modified bcp47 tag: b+en+Latn+US.
        void appendDirLocale(String8& str) const;

        // Sets the values of language, region, script, variant and numbering
        // system to the well formed BCP 47 locale contained in |in|.
        // The input locale is assumed to be valid and no validation is performed.
        void setBcp47Locale(const char* in);

        inline void clearLocale() {
        locale = 0;
        localeScriptWasComputed = false;
        memset(localeScript, 0, sizeof(localeScript));
        memset(localeVariant, 0, sizeof(localeVariant));
        memset(localeNumberingSystem, 0, sizeof(localeNumberingSystem));
        }

        inline void computeScript() {
        localeDataComputeScript(localeScript, language, country);
        }

        // Get the 2 or 3 letter language code of this configuration. Trailing
        // bytes are set to '\0'.
        size_t unpackLanguage(char language[4]) const;
        // Get the 2 or 3 letter language code of this configuration. Trailing
        // bytes are set to '\0'.
        size_t unpackRegion(char region[4]) const;

        // Sets the language code of this configuration to the first three
        // chars at |language|.
        //
        // If |language| is a 2 letter code, the trailing byte must be '\0' or
        // the BCP-47 separator '-'.
        void packLanguage(const char* language);
        // Sets the region code of this configuration to the first three bytes
        // at |region|. If |region| is a 2 letter code, the trailing byte must be '\0'
        // or the BCP-47 separator '-'.
        void packRegion(const char* region);

        // Returns a positive integer if this config is more specific than |o|
        // with respect to their locales, a negative integer if |o| is more specific
        // and 0 if they're equally specific.
        int isLocaleMoreSpecificThan(const ResTable_config &o) const;

        // Returns an integer representng the imporance score of the configuration locale.
        int getImportanceScoreOfLocale() const;

        // Return true if 'this' is a better locale match than 'o' for the
        // 'requested' configuration. Similar to isBetterThan(), this assumes that
        // match() has already been used to remove any configurations that don't
        // match the requested configuration at all.
        bool isLocaleBetterThan(const ResTable_config& o, const ResTable_config* requested) const;

        String8 toString() const;
        };*/

public class ResTableConfig {

    int size;

    short mcc;

    short mnc;

    int imsi;

    char[] language = new char[2];

    char[] country = new char[2];

    int locale;


    byte orientation;

    byte touchscreen;

    short density;

    int screenType;


    byte keyboard;

    byte navigation;

    byte inputFlags;

    byte inputPad0;

    int input;

    short screenWidth;
    short screenHeight;
    int screenSize;

    short sdkVersion;
    short minorVersion;
    int version;

    byte screenLayout;
    byte uiMode;
    short smallestScreenWidthDp;
    int screenConfig;

    short screenWidthDp;
    short screenHeightDp;
    int screenSizeDp;

    char[] localeScript = new char[4];

    char[] localeVariant = new char[8];


    byte screenLayout2;      // Contains round/notround qualifier.
    byte colorMode;          // Wide-gamut, HDR, etc.
    short screenConfigPad2;  // Reserved padding.
    int screenConfig2;

    boolean localeScriptWasComputed;

    char[] localeNumberingSystem = new char[8];


    public void parse(ByteBuffer byteBuffer) {
        size = byteBuffer.getInt();
    }
}
