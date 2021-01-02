class LonggestValidParentheses(object):
    def longestValidParentheses(self, s):
        """
        :type s: str
        :rtype: int
        """
        stack = [-1]
        l = 0
        for i, c in enumerate(s):
            if c == "(":
                stack.append(i)
            else:
                stack.pop()
                if not stack:
                    stack.append(i)
                else:
                    l = max(l, i - stack[len(stack) - 1])
        print(l)
        return l


LonggestValidParentheses().longestValidParentheses("((()))))")
