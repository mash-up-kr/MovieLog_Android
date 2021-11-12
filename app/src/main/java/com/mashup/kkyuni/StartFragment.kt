package com.mashup.kkyuni

import androidx.fragment.app.Fragment

/**
 * 실제로는 사용하지 않는 Fragment.
 * 화면 플로우에 맞게 순서대로 개발되지 않기 때문에
 * 해당 Fragment 뒤에 각자 개발하는 Fragment 를 붙여 테스트 합니다.
 * Commit 할 땐 해당 Fragment 를 제외합니다.
 * 만약 직전 Fragment 가 완성되어 merge 된 상태라면 플로우에 맞게 연결 후 push 합니다.
 *
 * Todo: 개발이 완료되거나 더 이상 불필요하다고 판단되는 시점에서 해당 Fragment 를 완전히 제거합니다.
 */
class StartFragment : Fragment()