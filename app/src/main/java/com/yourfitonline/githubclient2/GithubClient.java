package com.yourfitonline.githubclient2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Интерфейс клиента, в котором мы указываем нужные нам методы
 */

public interface GithubClient {
    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> contributors(
            @Path("owner") String owner,
            @Path("repo") String repo);

    @GET("/users/{username}")
    Call<GithubUser> githubUser(
            @Path("name") String name,
            @Path("login") String login,
            @Path("id") long id,
            @Path("avatar_url") String avatar_url,
            @Path("gravatar_id") int gravatar_id,
            @Path("url") String url,
            @Path("html_url") String html_url,
            @Path("followers_url") String followers_url,
            @Path("following_url") String following_url,
            @Path("gists_url") String hists_url,
            @Path("starred_url") String starred_url,
            @Path("subscriptions_url") String subscriptions_url,
            @Path("organizations_url") String organizations_url,
            @Path("repos_url") String repos_url,
            @Path("events_url") String events_url,
            @Path("received_events_url") String received_events_url,
            @Path("type") String type,
            @Path("site_admin") Boolean site_admin,
            @Path("company") String company,
            @Path("blog") String blog,
            @Path("location") String location,
            @Path("email") String email,
            @Path("hireable") String hireable,
            @Path("bio") String bio,
            @Path(  "public_repos") int public_repos,
            @Path( "public_gists") int public_gists,
            @Path( "followers") int followers,
            @Path( "following") int following,
            @Path( "created_at") String created_at,
            @Path( "updated_at") String updated_at
    );
}
