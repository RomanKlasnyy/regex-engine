regex = input()
rl = regex.split('|')
reg_list = list(rl[0])
str_list = list(rl[1])
result = False
chars_match = 0
nested_break = False

if len(reg_list) > len(str_list):
    result = False
elif not reg_list and not str_list:
    result = True
elif not reg_list:
    result = True
elif not str_list:
    result = False
else:
    for i in range(len(str_list)):
        if nested_break:
            break
        if reg_list[0] == str_list[i] or reg_list[0] == '.':
            if len(reg_list) > 1:
                chars_match = 1
                for k in range(len(reg_list)-1):
                    try:
                        if reg_list[k+1] == str_list[i+k+1] or reg_list[k+1] == '.':
                            chars_match += 1
                            if chars_match == len(reg_list):
                                result = True
                                nested_break = True
                                break
                    except IndexError:
                        result = False
                        break
            else:
                result = True

print(result)

# Examples:
# Input: 'apple|apple'     Output: True
# Input:    'ap|apple'     Output: True
# Input:    'le|apple'     Output: True
# Input:     'a|apple'     Output: True
# Input:     '.|apple'     Output: True
# Input: 'apwle|apple'     Output: False
# Input: 'peach|apple'     Output: False
