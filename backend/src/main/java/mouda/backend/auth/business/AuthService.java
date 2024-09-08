package mouda.backend.auth.business;

import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import mouda.backend.auth.Infrastructure.KakaoOauthClient;
import mouda.backend.auth.implement.DarakbangFinder;
import mouda.backend.auth.implement.DarakbangMemberFinder;
import mouda.backend.auth.implement.JwtProvider;
import mouda.backend.auth.implement.MemberFinder;
import mouda.backend.auth.presentation.request.OauthRequest;
import mouda.backend.auth.presentation.response.LoginResponse;
import mouda.backend.auth.util.TokenDecoder;
import mouda.backend.darakbang.domain.Darakbang;
import mouda.backend.darakbangmember.domain.DarakbangMember;
import mouda.backend.member.domain.Member;
import mouda.backend.member.infrastructure.MemberRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final JwtProvider jwtProvider;
	private final MemberRepository memberRepository;
	private final KakaoOauthClient kakaoOauthClient;
	private final MemberFinder memberFinder;
	private final DarakbangFinder darakbangFinder;
	private final DarakbangMemberFinder darakbangMemberFinder;

	public LoginResponse oauthLogin(OauthRequest oauthRequest) {
		String kakaoIdToken = kakaoOauthClient.getIdToken(oauthRequest.code());

		Map<String, String> payload = TokenDecoder.parseKakaoToken(kakaoIdToken);
		String kakaoId = payload.get("sub");

		return processLogin(Long.parseLong(kakaoId));
	}

	private LoginResponse processLogin(Long kakaoId) {
		return memberRepository.findByKakaoId(kakaoId)
			.map(member -> {
				String token = jwtProvider.createToken(member);
				return new LoginResponse(token);
			})
			.orElseGet(() -> {
				Member newMember = Member.builder()
					.kakaoId(kakaoId)
					.build();
				memberRepository.save(newMember);
				String token = jwtProvider.createToken(newMember);
				return new LoginResponse(token);
			});
	}

	public Member findMember(String token) {
		long memberId = jwtProvider.extractMemberId(token);
		return memberFinder.find(memberId);
	}

	public DarakbangMember findDarakbangMember(long darakbangId, Member member) {
		Darakbang darakbang = darakbangFinder.find(darakbangId);
		return darakbangMemberFinder.find(darakbang, member);
	}

	public void checkAuthentication(String token) {
		jwtProvider.validateExpiration(token);
	}

	public LoginResponse basicLogin() {
		Member member = new Member("nickname", 1L);
		memberRepository.save(member);
		return new LoginResponse(jwtProvider.createToken(member));
	}
}
