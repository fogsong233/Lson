package cn.luern0313.lson.path;

/**
 * 被 luern0313 创建于 2020/8/10.
 */

enum Status
{
    STATUS_EXPECT_JSON_ROOT,
    STATUS_EXPECT_POINT,
    STATUS_EXPECT_COLON,
    STATUS_EXPECT_COMMA,
    STATUS_EXPECT_PATH,
    STATUS_EXPECT_EXPRESSION_START,
    STATUS_EXPECT_EXPRESSION_END,
    STATUS_EXPECT_NUMBER,
    STATUS_EXPECT_END_DOCUMENT;

    int index;

    private Status()
    {
        this.index = 1 << ordinal();
    }
}
