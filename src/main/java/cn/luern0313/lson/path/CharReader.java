package cn.luern0313.lson.path;

import java.io.IOException;
import java.io.Reader;

/**
 * 被 luern0313 创建于 2020/8/8.
 */

class CharReader
{
    static final int BUFFER_SIZE = 1024;

    // total readed chars:
    int readed = 0;

    // buffer position:
    int pos = 0;

    // buffer ends:
    int size = 0;

    final char[] buffer;
    final Reader reader;

    public CharReader(Reader reader)
    {
        this.buffer = new char[BUFFER_SIZE];
        this.reader = reader;
    }

    public boolean hasMore()
    {
        if(pos < size)
        {
            return true;
        }
        fillBuffer(null);
        return pos < size;
    }

    public String next(int size)
    {
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < size; i++)
        {
            sb.append(next());
        }
        return sb.toString();
    }

    public char next()
    {
        if(this.pos == this.size)
        {
            fillBuffer("EOF");
        }
        char ch = this.buffer[this.pos];
        this.pos++;
        return ch;
    }

    public char peek()
    {
        if(this.pos == this.size)
        {
            // fill buffer:
            fillBuffer("EOF");
        }
        assert (this.pos < this.size);
        return this.buffer[this.pos];
    }

    void fillBuffer(String eofErrorMessage)
    {
        try
        {

            int n = reader.read(buffer);
            if(n == (-1))
            {
                if(eofErrorMessage != null)
                {
                    throw new PathParseException(eofErrorMessage, this.readed);
                }
                return;
            }
            this.pos = 0;
            this.size = n;
            this.readed += n;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
