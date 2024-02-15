package main

import (
	"fmt"
	"strings"
)

func compareChar(regex byte, ch byte) bool {
	return regex == ch || regex == '.' || regex == 0
}

func compareEqStr(regex string, str string) bool {
	if regex == "" || (regex == "$" && str == "") {
		return true
	} else if len(regex) > 1 && regex[0] == '\\' && str != "" {
		return compareEqStr(regex[2:], str[1:])
	} else if len(regex) > 1 && regex[1] == '?' && str != "" {
		return compareEqStr(regex[2:], str) || compareEqStr(string(regex[0])+regex[2:], str)
	} else if len(regex) > 1 && regex[1] == '*' && str != "" {
		return compareEqStr(regex[2:], str) || compareEqStr(regex, str[1:])
	} else if len(regex) > 1 && regex[1] == '+' && str != "" {
		return compareEqStr(string(regex[0])+regex[2:], str) || compareEqStr(regex, str[1:])
	} else if str == "" || !compareChar(regex[0], str[0]) {
		return false
	} else {
		return compareEqStr(regex[1:], str[1:])
	}
}

func compareStr(regex string, str string) bool {
	if str == "" && regex != "" {
		return false
	} else if compareEqStr(regex, str) {
		return true
	} else if regex[0] == '^' {
		return compareEqStr(regex[1:], str)
	} else {
		return compareStr(regex, str[1:])
	}
}

func main() {
	var regex, str string
	fmt.Scanln(&regex, &str)
	inputs := strings.Split(regex+"|"+str, "|")
	regex = inputs[0]
	str = inputs[1]
	fmt.Println(compareStr(regex, str))
}
